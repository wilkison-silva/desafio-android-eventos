package br.com.wilkison.desafio.extensions

import android.widget.ImageView
import br.com.wilkison.desafio.R
import coil.load

fun ImageView.tryLoadImage(imageUrl: String? = null){
    load(imageUrl){
        crossfade(enable = true)
        fallback(R.drawable.ic_image_fallback)
        error(R.drawable.ic_image_error)
        placeholder(R.drawable.ic_placeholder)
    }
}