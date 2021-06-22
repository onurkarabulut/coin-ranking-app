package com.onurkarabulut.coin_ranking_app.adapter

import com.onurkarabulut.coin_ranking_app.model.CoinHistoryResult
import com.robinhood.spark.SparkAdapter

class CoinDetailSparkAdapter(private val coinData : List<CoinHistoryResult.Data.History>): SparkAdapter(){
    override fun getCount() = coinData.size

    override fun getItem(index: Int): Any = coinData[index]

    override fun getY(index: Int): Float {
        val chosenTimeData = coinData[index]
        return chosenTimeData.price
    }
}