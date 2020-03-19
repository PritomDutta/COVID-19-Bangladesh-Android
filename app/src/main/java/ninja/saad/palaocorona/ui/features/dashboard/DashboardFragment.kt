package ninja.saad.palaocorona.ui.features.dashboard

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_dashboard.*
import ninja.saad.palaocorona.R
import ninja.saad.palaocorona.base.ui.BaseFragment
import org.jetbrains.anko.sdk27.coroutines.onClick

class DashboardFragment: BaseFragment<DashboardViewModel>() {


    override val layoutId: Int
        get() = R.layout.fragment_dashboard
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        gujobSlider.sliderAdapter = SliderAdapter()
        gujobSlider.startAutoCycle()
        setClickListener()
    }
    
    private fun setClickListener() {
        
        btnTest.onClick {
        
        }
        
        btnNews.onClick {
        
        }
        btnDos.onClick {
        
        }
        btnQuarantine.onClick {
        
        }
        btnFaq.onClick {
        
        }
        btnMessage.onClick {
        
        }
    }
}