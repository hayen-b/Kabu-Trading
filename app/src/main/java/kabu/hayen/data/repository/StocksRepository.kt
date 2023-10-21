package kabu.hayen.data.repository

import kabu.hayen.data.model.BoughtStock

interface StocksRepository {
    suspend fun addStock(stock: BoughtStock)
    suspend fun getBoughtStock(username: String, symbol: String): BoughtStock?
    suspend fun getBoughtStocks(username: String): List<BoughtStock>?
    suspend fun deleteStock(username: String)
}