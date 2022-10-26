package br.com.wilkison.desafio.data.model

import br.com.wilkison.desafio.domain.model.ShortEventDomain

data class ShortEventData(
    val date: String?,
    val price: Double,
    val title: String,
    val id: String
) {
    fun convertToShortEventDomain(): ShortEventDomain {
        return ShortEventDomain(
            date = date,
            price = price,
            title = title,
            id = id
        )
    }
}