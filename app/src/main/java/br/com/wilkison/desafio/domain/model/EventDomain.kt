package br.com.wilkison.desafio.domain.model

import br.com.wilkison.desafio.presentation.model.EventView

data class EventDomain(
    val date: Int,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String,
    val id: String
) {
    fun convertToEventView(): EventView {
        return EventView(
            date = date,
            description = description,
            image = image,
            longitude = longitude,
            latitude = latitude,
            price = price,
            title = title,
            id = id
        )
    }
}