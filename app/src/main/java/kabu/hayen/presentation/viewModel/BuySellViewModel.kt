package kabu.hayen.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import kabu.hayen.data.model.AccountInfo
import kabu.hayen.data.model.BoughtStock
import kabu.hayen.data.model.PortfolioHistory

interface BuySellViewModel {
    val accResponse: MutableLiveData<AccountInfo?>
    val portResponse: MutableLiveData<List<PortfolioHistory>?>
    val stockResponse: MutableLiveData<BoughtStock?>
    val stocksResponse: MutableLiveData<List<BoughtStock>?>

    fun addAccountInfo(accountInfo: AccountInfo)
    fun getAccountInfo(username: String)

    fun addPortfolioHistory(portfolio: PortfolioHistory)
    fun getPortfolioHistory(username: String)

    fun addBoughtStock(stock: BoughtStock)
    fun getBoughtStock(username: String, symbol: String)
    fun getBoughtStocks(username: String)
    fun deleteStock(username: String)
}