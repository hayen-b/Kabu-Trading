package kabu.hayen.data.repository

import android.util.Log
import kabu.hayen.data.dataSource.remote.QuoteRemoteDs
import kabu.hayen.data.dataSource.remote.Retrofit
import kabu.hayen.data.model.Chart
import kabu.hayen.data.model.Stock
import kabu.hayen.data.model.StockMetrics
import java.lang.Exception

class DetailsRepositoryImpl: DetailsRepository {
    override suspend fun fetchStock(symbol: String): Stock {
        // remote data source
        return try {
            Retrofit
                .getInstance("https://testapi.io")
                .create(QuoteRemoteDs::class.java)
                .fetchStock() // ovde bi isao symbol za query param
                .data
        } catch (e: Exception) {
            Log.d("ERROR", "Retrofit - Details")
            Stock("INVALID", "INVALID", "INVALID", "INVALID",
                0.toDouble(), 0.toDouble(), 0.toDouble(), 0.toDouble(), 0.toDouble(),
                StockMetrics(0.toDouble(), 0.toDouble(), 0.toDouble(), 0.toDouble(), 0.toDouble(), 0.toDouble()),
                Chart(ArrayList())
            )
        }
    }
}