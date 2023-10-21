package kabu.hayen.data.repository

import kabu.hayen.data.model.News
import kabu.hayen.data.model.Quote

interface NewsRepository {
    suspend fun fetchNews(): List<News>
    suspend fun fetchStocks(): List<Quote>
}