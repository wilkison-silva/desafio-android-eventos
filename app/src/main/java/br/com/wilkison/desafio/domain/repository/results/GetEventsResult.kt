package br.com.wilkison.desafio.domain.repository.results

import br.com.wilkison.desafio.domain.model.ShortEventDomain

sealed class GetEventsResult {
    data class Success(val shortEventDomainList: List<ShortEventDomain>) : GetEventsResult()
    object Error : GetEventsResult()
}