package com.onurkarabulut.coin_ranking_app.util
import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.decode.SvgDecoder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.onurkarabulut.coin_ranking_app.R
import com.onurkarabulut.coin_ranking_app.model.CoinHistoryResult
import java.text.SimpleDateFormat
import java.util.*



fun ImageView.loadSvg(url: String) { /*for svg files*/
    val imageLoader = coil.ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
            .build()

    val request = coil.request.ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(10)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .data(url)
            .target(this)
            .build()

    imageLoader.enqueue(request)
}


