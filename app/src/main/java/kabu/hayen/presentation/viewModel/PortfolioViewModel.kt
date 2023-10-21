package kabu.hayen.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import kabu.hayen.data.model.AccountInfo
import kabu.hayen.data.model.BoughtStock
import kabu.hayen.data.model.PortfolioHistory
import kabu.hayen.data.model.Stock

interface PortfolioViewModel {
    val accountResponse: MutableLiveData<AccountInfo?>
    val portfolioResponse: MutableLiveData<List<PortfolioHistory>?>
    val stocksResponse: MutableLiveData<List<BoughtStock>?>
    val quoteResponse: MutableLiveData<Stock>

    fun getAccountInfo(username: String)
    fun getPortfolioHistory(username: String)
    fun getBoughtStocks(username: String)

    fun fetchStock(symbol: String)
}