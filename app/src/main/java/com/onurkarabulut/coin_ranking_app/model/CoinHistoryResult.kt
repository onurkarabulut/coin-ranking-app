package com.onurkarabulut.coin_ranking_app.model

import com.google.gson.annotations.SerializedName

data class CoinHistoryResult(
    @SerializedName("status")
    var status: String,
    @SerializedName("data")
    var `data`: Data
    ) {
        data class Data(
            @SerializedName("change")
            var change: Double,
            @SerializedName("history")
            var history: List<History>
        ) {
            data class History(
                @SerializedName("price")
                var price: Float,
                @SerializedName("timestamp")
                var timestamp: Long
            )
        }
}