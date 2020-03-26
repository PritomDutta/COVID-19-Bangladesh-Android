package xyz.palaocorona.ui.features.news

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import xyz.palaocorona.base.ui.BaseViewModel
import xyz.palaocorona.data.news.NewsRepository
import xyz.palaocorona.data.news.model.News
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository): BaseViewModel() {
    fun getNews(currentLanguage: String) {
    
    }
    
    var news = MutableLiveData<MutableList<News>>()
    
    fun getNews(language: String, visibleItemCount: Int) {
        val disposable = newsRepository.getNews(language, (visibleItemCount / 10) + 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loader.value = true }
            .doAfterTerminate { loader.value = false }
            .subscribe({
                this.news.value = it
            }, {
                it.printStackTrace()
            })
        compositeDisposable.add(disposable)
    }
}