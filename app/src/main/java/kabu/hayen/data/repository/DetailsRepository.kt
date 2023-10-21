package kabu.hayen.data.repository

import kabu.hayen.data.model.Stock

interface DetailsRepository {
    suspend fun fetchStock(symbol: String): Stock
}