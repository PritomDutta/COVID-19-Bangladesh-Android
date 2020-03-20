package ninja.saad.palaocorona.ui.features.news

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import ninja.saad.palaocorona.R
import ninja.saad.palaocorona.base.ui.BaseFragment

class NewsFragment: BaseFragment<NewsViewModel>()  {
    
    override val layoutId: Int
        get() = R.layout.fragment_news
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        rvNews.layoutManager = LinearLayoutManager(context)
        rvNews.adapter = NewsAdapter()
        
        viewModel.news.observe(viewLifecycleOwner, Observer {
            (rvNews.adapter as NewsAdapter).setItems(it)
        })
        
        //viewModel.getNews()
    }
}