package br.com.wilkison.desafio.extensions

import android.view.View
import br.com.wilkison.desafio.R
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String) {
    Snackbar
        .make(this, message, Snackbar.LENGTH_LONG)
        .setAction(context.getString(R.string.close)) {}
        .show()
}