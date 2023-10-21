package kabu.hayen.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import kabu.hayen.data.model.BoughtStock
import kabu.hayen.data.model.Stock

interface DetailsViewModel {
    val stock: MutableLiveData<Stock>
    val boughtStock: MutableLiveData<BoughtStock?>

    fun fetchStock(symbol: String)
    fun getBoughtStock(username: String, symbol: String)
}