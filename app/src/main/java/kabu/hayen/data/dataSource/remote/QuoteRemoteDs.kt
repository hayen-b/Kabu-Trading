package kabu.hayen.data.dataSource.remote

import retrofit2.http.GET
import kabu.hayen.data.model.StockResponse

interface QuoteRemoteDs {
    // *** trebalo bi ovako ali API koji koristim ne dozvoljava query param ***
    //@GET("api/ngrujic00/searchQuote")
    //suspend fun fetchStock(@Query("symbol") symbol: String): StockResponse

    @GET("api/ngrujic00/searchQuote_symbol=T")
    suspend fun fetchStock(): StockResponse
}