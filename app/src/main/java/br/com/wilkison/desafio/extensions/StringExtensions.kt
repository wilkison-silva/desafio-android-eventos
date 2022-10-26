package br.com.wilkison.desafio.extensions

import java.text.SimpleDateFormat
import java.util.*

private const val SEM_DATA_DEFINIDA = "Sem data definida"

fun String?.convertTimestampToDate(): String {
    return try {
        this?.let {
            val date = Date(it.toLong())
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "br"))
            format.format(date)
        } ?: SEM_DATA_DEFINIDA
    } catch (e: Exception) {
        SEM_DATA_DEFINIDA
    }
}