package com.onurkarabulut.coin_ranking_app.model
import com.google.gson.annotations.SerializedName


data class CoinResult(
        @SerializedName("status")
        var status: String?,
        @SerializedName("data")
        var `data`: Data?
) {
    data class Data(
            @SerializedName("stats")
            var stats: Stats?,
            @SerializedName("coins")
            var coins: List<Coin>?
    ) {
        data class Stats(
                @SerializedName("total")
                var total: Int?,
                @SerializedName("totalMarkets")
                var totalMarkets: Int?,
                @SerializedName("totalExchanges")
                var totalExchanges: Int?,
                @SerializedName("totalMarketCap")
                var totalMarketCap: Double?,
                @SerializedName("total24hVolume")
                var total24hVolume: Double?
        )
        data class Coin(
                @SerializedName("uuid")
                var uuid: String?,
                @SerializedName("symbol")
                var symbol: String?,
                @SerializedName("name")
                var name: String?,
                @SerializedName("color")
                var color: String?,
                @SerializedName("iconUrl")
                var iconUrl: String?,
                @SerializedName("marketCap")
                var marketCap: Double?,
                @SerializedName("price")
                var price: Double?,
                @SerializedName("listedAt")
                var listedAt: Int?,
                @SerializedName("tier")
                var tier: Int?,
                @SerializedName("change")
                var change: Double?,
                @SerializedName("rank")
                var rank: Int?,
                @SerializedName("sparkLine")
                var sparkLine: List<String>?,
                @SerializedName("lowVolume")
                var lowVolume: Boolean?,
                @SerializedName("coinrankingUrl")
                var coinrankingUrl: String?,
                @SerializedName("24hVolume")
                var `24hVolume`: String?,
                @SerializedName("btcPrice")
                var btcPrice: String?
        )
    }
}