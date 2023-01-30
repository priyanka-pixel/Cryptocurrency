package com.example.cryptocurrency.data.remote

import com.example.cryptocurrency.data.remote.dto.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{id}")
    suspend fun getCoinById(@Path("id") id: String): CoinDetailDto


    companion object {
        const val BASE_URL = "https://api.coinpaprika.com/"

        const val COIN_ID = "id"

    }
}