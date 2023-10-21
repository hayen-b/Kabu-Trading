package kabu.hayen.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kabu.hayen.data.model.News
import kabu.hayen.data.model.Quote
import kabu.hayen.data.repository.NewsRepository

class DiscoverViewModelImpl : DiscoverViewModel, ViewModel(), KoinComponent {
    private val newsRepo: NewsRepository by inject()
    override val newsResponse: MutableLiveData<List<News>> = MutableLiveData()
    override val indexResponse: MutableLiveData<List<Quote>> = MutableLiveData()

    override fun fetchNews() {
        viewModelScope.launch {
            val response = newsRepo.fetchNews()
            newsResponse.value = response
        }
    }

    override fun fetchStocks() {
        viewModelScope.launch {
            val response = newsRepo.fetchStocks()
            indexResponse.value = response
        }
    }
}