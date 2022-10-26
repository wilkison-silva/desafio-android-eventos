package br.com.wilkison.desafio.presentation.feature.event_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wilkison.desafio.domain.usecases.get_event_details.GetEventDetailsUseCase
import br.com.wilkison.desafio.domain.usecases.get_event_details.GetEventDetailsUseCaseResult
import kotlinx.coroutines.launch

class EventDetailsViewModel(
    private val getEventDetailsUseCase: GetEventDetailsUseCase
) : ViewModel() {


    fun getEventsList(eventId: String) {
        viewModelScope.launch {
            when (val getEventsListResult = getEventDetailsUseCase.getEventDetails(eventId = eventId)) {
                is GetEventDetailsUseCaseResult.Error -> {

                }
                is GetEventDetailsUseCaseResult.Success -> {

                }
            }
        }
    }
}