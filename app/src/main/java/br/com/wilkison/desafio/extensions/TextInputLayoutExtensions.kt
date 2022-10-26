package br.com.wilkison.desafio.extensions

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import br.com.wilkison.desafio.R
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.withError() {
    this.boxStrokeColor = ContextCompat
        .getColor(context, R.color.red)
    val colorInt = ContextCompat.getColor(context, R.color.red)
    val csl = ColorStateList.valueOf(colorInt)
    this.hintTextColor = csl
}

fun TextInputLayout.withoutError() {
    this.error = null
}
