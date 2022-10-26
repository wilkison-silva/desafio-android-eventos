package br.com.wilkison.desafio.domain.usecases.get_events_list

import br.com.wilkison.desafio.presentation.model.EventView

sealed class GetEventsListUseCaseResult {
    data class Success (val eventViewList: List<EventView>) : GetEventsListUseCaseResult()
    object Error: GetEventsListUseCaseResult()
}