package br.com.wilkison.desafio.domain.usecases.get_event_details

import br.com.wilkison.desafio.presentation.model.EventView

sealed class GetEventDetailsUseCaseResult {
    data class Success (val eventView: EventView) : GetEventDetailsUseCaseResult()
    object Error: GetEventDetailsUseCaseResult()
}