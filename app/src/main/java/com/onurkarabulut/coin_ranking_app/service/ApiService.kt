package com.onurkarabulut.coin_ranking_app.service

import com.onurkarabulut.coin_ranking_app.model.SingleCoinResult
import com.onurkarabulut.coin_ranking_app.model.CoinHistoryResult
import com.onurkarabulut.coin_ranking_app.model.CoinResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("x-access-token: coinrankingf7537d99ea92a2d2aed2776fdd5c1efae15c8e4f4fa34b96")
    @GET("coins")
    fun getAllCoins(): Call<CoinResult>

    @Headers("x-access-token: coinrankingf7537d99ea92a2d2aed2776fdd5c1efae15c8e4f4fa34b96")
    @GET("coin/{id}")
    fun getCoin(@Path("id") coinId : String): Call<SingleCoinResult>

    @Headers("x-access-token: coinrankingf7537d99ea92a2d2aed2776fdd5c1efae15c8e4f4fa34b96")
    @GET("coin/{id}/history")
    fun getCoinHistory(@Path("id") coinId : String, @Query("timePeriod")datePeriod : String): Call<CoinHistoryResult>




}