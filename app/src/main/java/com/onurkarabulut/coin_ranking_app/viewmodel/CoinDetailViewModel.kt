package com.onurkarabulut.coin_ranking_app.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.onurkarabulut.coin_ranking_app.model.SingleCoinResult
import com.onurkarabulut.coin_ranking_app.model.CoinHistoryResult
import com.onurkarabulut.coin_ranking_app.service.ApiClient
import com.onurkarabulut.coin_ranking_app.service.ApiService
import com.onurkarabulut.coin_ranking_app.util.Constant

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class CoinDetailViewModel(application: Application): AndroidViewModel(application) {
    private val apiClient = ApiClient()
    val coinLoading = MutableLiveData<Boolean>()
    val coinData = MutableLiveData<SingleCoinResult>()
    val coinHistory = MutableLiveData<CoinHistoryResult>()

    fun getCoinHistory(coinId : String, date : String){

        apiClient.api.getCoinHistory(coinId,date).enqueue(object : Callback<CoinHistoryResult>{
            override fun onResponse(
                call: Call<CoinHistoryResult>,
                response: Response<CoinHistoryResult>
            ) {
                coinHistory.value = response.body()

            }
            override fun onFailure(call: Call<CoinHistoryResult>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


    fun getSingleCoin(coinId: String){
        coinLoading.value = true
        apiClient.api.getCoin(coinId).enqueue(object : Callback<SingleCoinResult>{
            override fun onResponse(
                call: Call<SingleCoinResult>,
                response: Response<SingleCoinResult>
            ) {
                coinData.value = response.body()
                coinLoading.value = false
            }
            override fun onFailure(call: Call<SingleCoinResult>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }

}