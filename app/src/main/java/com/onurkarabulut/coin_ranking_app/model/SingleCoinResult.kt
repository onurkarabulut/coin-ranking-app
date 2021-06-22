package com.onurkarabulut.coin_ranking_app.model

import com.google.gson.annotations.SerializedName

class SingleCoinResult (
    @SerializedName("status")
    var status: String,
    @SerializedName("data")
    var `data`: Data
) {
    data class Data(
        @SerializedName("coin")
        var coins: Coin?
    ) {
        data class Coin(
            @SerializedName("uuid")
            var uuid: String?,
            @SerializedName("symbol")
            var symbol: String?,
            @SerializedName("name")
            var name: String?,
            @SerializedName("description")
            var description : String?,
            @SerializedName("color")
            var color: String?,
            @SerializedName("iconUrl")
            var iconUrl: String?,
            @SerializedName("websiteUrl")
            var websiteUrl: String?,
            @SerializedName("marketCap")
            var marketCap: Double?,
            @SerializedName("price")
            var price: Double?,
            @SerializedName("btcPrice")
            var btcPrice: String?,
            @SerializedName("change")
            var change: Double?,
        )
    }
}