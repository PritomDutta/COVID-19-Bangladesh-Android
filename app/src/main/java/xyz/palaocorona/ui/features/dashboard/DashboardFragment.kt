package xyz.palaocorona.ui.features.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_dashboard.*
import xyz.palaocorona.R
import xyz.palaocorona.base.ui.BaseFragment
import xyz.palaocorona.ui.features.about.AboutCovidFragment
import xyz.palaocorona.ui.features.authentication.AuthenticationActivity
import xyz.palaocorona.ui.features.contact.ContactFragment
import xyz.palaocorona.ui.features.dosanddonts.DosAndDontsFragment
import xyz.palaocorona.ui.features.faq.FaqFragment
import xyz.palaocorona.ui.features.liveupdates.LiveUpdatesFragment
import xyz.palaocorona.ui.features.news.NewsFragment
import xyz.palaocorona.ui.features.quarantine.QuarantineFragment
import xyz.palaocorona.ui.features.testyourself.TestYourselfFragment
import org.jetbrains.anko.sdk27.coroutines.onClick

class DashboardFragment: BaseFragment<DashboardViewModel>() {
    
    private val LOGIN_REQUEST_CODE = 2592
    
    override val layoutId: Int
        get() = R.layout.fragment_dashboard
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        gujobSlider.sliderAdapter = SliderAdapter("dashboard", getCurrentLocale().language)
        //drop or swap
        gujobSlider.setIndicatorAnimation(IndicatorAnimations.DROP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        gujobSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        gujobSlider.scrollTimeInSec = 8 //set scroll delay in seconds :
        gujobSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
        gujobSlider.setSliderAnimationDuration(600)
        gujobSlider.setIndicatorAnimationDuration(600)
        gujobSlider.startAutoCycle()
        gujobSlider.isAutoCycle = true
        setClickListener()
        
        viewModel.sliderImages.observe(viewLifecycleOwner, Observer {
            (gujobSlider.sliderAdapter as SliderAdapter).addSliderToDashboard(it)
        })
        
        viewModel.isInternetAvailable.observeOn(viewLifecycleOwner, Observer {
            if(!it) showNoInternetConnectionDialog(null)
        })
        
        viewModel.getSliderImages()
    }
    
    private fun setClickListener() {
        
        btnAboutCovid.onClick {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragmentContainer, AboutCovidFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
        btnVirusTest.onClick {
            if(viewModel.isUserLoggedIn()) {
                startTest()
            } else {
                val intent = Intent(context, AuthenticationActivity::class.java)
                startActivityForResult(intent, LOGIN_REQUEST_CODE)
            }
        }
        btnRecentNews.onClick {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragmentContainer, NewsFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
        btnDosNDonts.onClick {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragmentContainer, DosAndDontsFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
        btnQuarantine.onClick {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragmentContainer, QuarantineFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
        btnFAQ.onClick {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragmentContainer, FaqFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
        btnLiveUpdates.onClick {
            viewModel.checkInternet {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.mainFragmentContainer, LiveUpdatesFragment())
                    ?.addToBackStack(null)
                    ?.commit()
            }
    
        }
        btnEmergency.onClick {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragmentContainer, ContactFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
        
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            startTest()
        }
    }
    
    private fun startTest() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.mainFragmentContainer, TestYourselfFragment())
            ?.addToBackStack(null)
            ?.commit()
    }
}