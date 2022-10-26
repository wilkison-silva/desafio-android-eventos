package br.com.wilkison.desafio.presentation.feature.list_events.states

import br.com.wilkison.desafio.presentation.model.ShortEventView

sealed class ListEventsState {
    object Loading : ListEventsState()
    data class Success (val shortEventViewList: List<ShortEventView>) : ListEventsState()
    object Error: ListEventsState()
}