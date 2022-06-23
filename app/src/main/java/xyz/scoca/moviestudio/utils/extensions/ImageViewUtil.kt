package xyz.scoca.moviestudio.utils.extensions

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jackandphantom.carouselrecyclerview.view.ReflectionImageView
import xyz.scoca.moviestudio.R

fun ImageView.loadImage(url: String?) {
    val placeHolder = createPlaceHolder(this.context)
    this.load(url) {
        crossfade(true)
        placeholder(placeHolder)
        crossfade(500)
    }
}

private fun createPlaceHolder(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 12f
        centerRadius = 40f
        start()
    }
}
