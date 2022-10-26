package br.com.wilkison.desafio.domain.usecases.get_events_list

import br.com.wilkison.desafio.presentation.model.ShortEventView

sealed class GetEventsListUseCaseResult {
    data class Success (val shortEventViewList: List<ShortEventView>) : GetEventsListUseCaseResult()
    object Error: GetEventsListUseCaseResult()
}