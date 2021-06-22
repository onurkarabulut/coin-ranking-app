package com.onurkarabulut.coin_ranking_app.viewmodel
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.onurkarabulut.coin_ranking_app.model.CoinResult
import com.onurkarabulut.coin_ranking_app.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CoinListViewModel(application: Application) : AndroidViewModel(application) {
    private val apiClient = ApiClient()
    private val TAG = "CoinListViewModel"
    val coinList = MutableLiveData<CoinResult>()
    val coinLoading = MutableLiveData<Boolean>()


    fun getDatasFromAPI(){
        coinLoading.value = true
       apiClient.api.getAllCoins().enqueue(object : Callback<CoinResult>{
           override fun onResponse(call: Call<CoinResult>, response: Response<CoinResult>) {
               coinList.value = response.body()
               coinLoading.value = false
           }
           override fun onFailure(call: Call<CoinResult>, t: Throwable) {
               Log.e(TAG, "onFailure $t")
           }
       })
    }
}