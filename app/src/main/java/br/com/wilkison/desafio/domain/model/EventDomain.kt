package br.com.wilkison.desafio.domain.model

import br.com.wilkison.desafio.presentation.model.EventView
import java.math.BigDecimal
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val SEM_DATA_DEFINIDA = "Sem data definida"

data class EventDomain(
    val date: String,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String,
    val id: String,
) {
    fun convertToEventView(): EventView {
        return EventView(
            date = getDateTimeFromTimeStamp(timestamp = date),
            description = description,
            image = image,
            longitude = longitude,
            latitude = latitude,
            price = formatToBrazilianReal(price.toBigDecimal()),
            title = title,
            id = id
        )
    }

    private fun getDateTimeFromTimeStamp(timestamp: String): String {
        return try {
            val date = Date(timestamp.toLong())
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "br"))
            format.format(date)
        } catch (e: Exception) {
            SEM_DATA_DEFINIDA
        }
    }

    private fun formatToBrazilianReal(value: BigDecimal): String {
        val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        return formatter.format(value)
    }
}