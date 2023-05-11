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
    val c: String
    )

interface ApiService {
    @GET("api-v3/forex/latest")
    suspend fun getForexList(
        @Query("symbol") symbol: String,
        @Query("access_key") accessKey: String
    ): FCSResponse
}

object RetrofitInstance {

    //https://api.freecurrencyapi.com/v1/latest?apikey=1XnSbUmZKCZjhU6ULxSP6D9j94lC2g8iHoLNDpEo&currencies=EUR%2CUSD%2CCAD
    private const val BASE_URL = "https://fcsapi.com/"

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
