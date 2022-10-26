package br.com.wilkison.desafio.data.model

import br.com.wilkison.desafio.domain.model.EventDomain

data class EventData(
    val date: String?,
    val description: String,
    val image: String,
    val longitude: Double?,
    val latitude: Double?,
    val price: Double,
    val title: String,
    val id: String
) {
    fun convertToEventDomain(): EventDomain {
        return EventDomain(
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