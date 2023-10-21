package kabu.hayen.data.dataSource.remote

import retrofit2.http.GET
import kabu.hayen.data.model.NewsResponse

interface NewsRemoteDs {
    @GET("api/ngrujic00/getNews")
    suspend fun fetchNews(): NewsResponse
}