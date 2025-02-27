package br.com.wilkison.desafio.domain.model

import br.com.wilkison.desafio.extensions.convertTimestampToDate
import br.com.wilkison.desafio.extensions.formatToBrazilianReal
import br.com.wilkison.desafio.presentation.model.EventView

data class EventDomain(
    val date: String?,
    val description: String,
    val image: String,
    val longitude: Double?,
    val latitude: Double?,
    val price: Double,
    val title: String,
    val id: String,
) {
    fun convertToEventView(): EventView {
        return EventView(
            date = date.convertTimestampToDate(),
            description = description,
            image = image,
            longitude = longitude,
            latitude = latitude,
            price = price.toBigDecimal().formatToBrazilianReal(),
            title = title,
            id = id
        )
    }
}