package com.onurkarabulut.coin_ranking_app.util
import android.widget.ImageView
import coil.decode.SvgDecoder
import com.onurkarabulut.coin_ranking_app.R
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


