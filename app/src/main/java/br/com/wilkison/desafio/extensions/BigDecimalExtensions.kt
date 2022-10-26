package br.com.wilkison.desafio.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun BigDecimal.formatToBrazilianReal(): String {
    val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatter.format(this)
}