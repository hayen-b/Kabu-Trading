package kabu.hayen.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import kabu.hayen.data.model.News
import kabu.hayen.data.model.Quote

interface DiscoverViewModel {
    val newsResponse: MutableLiveData<List<News>>
    val indexResponse: MutableLiveData<List<Quote>>

    fun fetchNews()
    fun fetchStocks()
}