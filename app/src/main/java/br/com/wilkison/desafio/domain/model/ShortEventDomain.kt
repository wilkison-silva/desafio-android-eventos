package br.com.wilkison.desafio.domain.model

import br.com.wilkison.desafio.extensions.convertTimestampToDate
import br.com.wilkison.desafio.extensions.formatToBrazilianReal
import br.com.wilkison.desafio.presentation.model.ShortEventView

data class ShortEventDomain(
    val date: String?,
    val price: Double,
    val title: String,
    val id: String,
) {

    fun convertToShortEventView(): ShortEventView {
        return ShortEventView(
            date = date.convertTimestampToDate(),
            price = price.toBigDecimal().formatToBrazilianReal(),
            title = title,
            id = id
        )
    }

}