package com.karamlyy.currencyapp

import kotlinx.serialization.Serializable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


@Serializable
data class FCSResponse(
    val response: List<ForexResponse>
)

@Serializable
data class ForexResponse(
    val o: String,
    val h: String,
    val l: String,
    val c: String,
    val ch: String,
    val cp: String,
    val t: String,
    val s: String,
    val tm: String
    )

interface ApiService {
    @GET("api-v3/forex/latest")
    suspend fun getForexList(
        @Query("symbol") symbol: String,
        @Query("access_key") accessKey: String
    ): FCSResponse
}

object RetrofitInstance {
    private const val BASE_URL = "https://fcsapi.com/"

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
