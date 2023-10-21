package kabu.hayen.presentation.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kabu.hayen.data.model.AccountInfo
import kabu.hayen.data.model.BoughtStock
import kabu.hayen.data.model.PortfolioHistory
import kabu.hayen.data.repository.*

class BuySellViewModelImpl : BuySellViewModel, ViewModel(), KoinComponent {
    private val accRepo: AccountRepository by inject()
    private val portRepo: PortfolioRepository by inject()
    private val stockRepo: StocksRepository by inject()
    override val accResponse: MutableLiveData<AccountInfo?> = MutableLiveData()
    override val portResponse: MutableLiveData<List<PortfolioHistory>?> = MutableLiveData()
    override val stockResponse: MutableLiveData<BoughtStock?> = MutableLiveData()
    override val stocksResponse: MutableLiveData<List<BoughtStock>?> = MutableLiveData()

    override fun addAccountInfo(accountInfo: AccountInfo) {
        viewModelScope.launch {
            accRepo.addAccountInfo(accountInfo)
        }
    }

    override fun getAccountInfo(username: String) {
        viewModelScope.launch {
            val response = accRepo.getAccountInfo(username)
            accResponse.value = response
        }
    }

    override fun addPortfolioHistory(portfolio: PortfolioHistory) {
        viewModelScope.launch {
            portRepo.addPortfolioHistory(portfolio)
        }
    }

    override fun getPortfolioHistory(username: String) {
        viewModelScope.launch {
            val response = portRepo.getPortfolioHistory(username)
            portResponse.value = response
        }
    }

    override fun addBoughtStock(stock: BoughtStock) {
        viewModelScope.launch {
            stockRepo.addStock(stock)
        }
    }

    override fun getBoughtStock(username: String, symbol: String) {
        viewModelScope.launch {
            val response = stockRepo.getBoughtStock(username, symbol)
            stockResponse.value = response
        }
    }

    override fun getBoughtStocks(username: String) {
        viewModelScope.launch {
            val response = stockRepo.getBoughtStocks(username)
            stocksResponse.value = response
        }
    }

    override fun deleteStock(username: String) {
        viewModelScope.launch {
            stockRepo.deleteStock(username)
        }
    }
}