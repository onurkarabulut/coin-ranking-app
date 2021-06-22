package com.onurkarabulut.coin_ranking_app.service

import com.onurkarabulut.coin_ranking_app.model.SingleCoinResult
import com.onurkarabulut.coin_ranking_app.model.CoinHistoryResult
import com.onurkarabulut.coin_ranking_app.model.CoinResult
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val BASE_URL = "https://api.coinranking.com/v2/"
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ApiService::class.java)


}