package kabu.hayen.data.dataSource.remote

import retrofit2.http.GET
import kabu.hayen.data.model.IndexesResponse

interface StocksRemoteDs {
    @GET("api/hayenb00/getIndexes")
    suspend fun fetchStocks(): IndexesResponse
}