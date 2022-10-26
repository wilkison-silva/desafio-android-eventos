package br.com.wilkison.desafio.presentation.feature.event_details.states

import br.com.wilkison.desafio.presentation.model.EventView

sealed class EventDetailsState {
    object Loading : EventDetailsState()
    data class Success (val eventView: EventView) : EventDetailsState()
    object Error: EventDetailsState()
}