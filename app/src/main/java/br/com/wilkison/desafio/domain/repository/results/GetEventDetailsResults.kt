package br.com.wilkison.desafio.domain.repository.results

import br.com.wilkison.desafio.domain.model.EventDomain

sealed class GetEventDetailsResults {
    data class Success(val eventDomain: EventDomain) : GetEventDetailsResults()
    object Error : GetEventDetailsResults()
}