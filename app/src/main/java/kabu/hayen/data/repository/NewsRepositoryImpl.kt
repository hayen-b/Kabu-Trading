package kabu.hayen.data.repository

import android.util.Log
import kabu.hayen.data.dataSource.remote.NewsRemoteDs
import kabu.hayen.data.dataSource.remote.Retrofit
import kabu.hayen.data.dataSource.remote.StocksRemoteDs
import kabu.hayen.data.model.News
import kabu.hayen.data.model.Quote

class NewsRepositoryImpl: NewsRepository {
    override suspend fun fetchNews(): List<News> {
        // remote data source
        return try {
            Retrofit
                .getInstance("https://testapi.io")
                .create(NewsRemoteDs::class.java)
                .fetchNews()
                .data.newsItems
        } catch (e: Exception) {
            Log.d("ERROR", "Retrofit - News")
            ArrayList()
        }
    }

    override suspend fun fetchStocks(): List<Quote> {
        // remote data source
        return try {
            Retrofit
                .getInstance("https://testapi.io")
                .create(StocksRemoteDs::class.java)
                .fetchStocks()
                .data.quotes
        } catch (e: Exception) {
            Log.d("ERROR", "Retrofit - Stocks")
            ArrayList()
        }
    }
}