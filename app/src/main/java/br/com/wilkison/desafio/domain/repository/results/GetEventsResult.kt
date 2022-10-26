package br.com.wilkison.desafio.domain.repository.results

import br.com.wilkison.desafio.domain.model.EventDomain

sealed class GetEventsResult {
    data class Success(val eventDomainList: List<EventDomain>) : GetEventsResult()
    object Error : GetEventsResult()
}