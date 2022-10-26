package br.com.wilkison.desafio.presentation.feature.event_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wilkison.desafio.domain.usecases.get_event_details.GetEventDetailsUseCase
import br.com.wilkison.desafio.domain.usecases.get_event_details.GetEventDetailsUseCaseResult
import br.com.wilkison.desafio.presentation.feature.event_details.states.EventDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EventDetailsViewModel(
    private val getEventDetailsUseCase: GetEventDetailsUseCase
) : ViewModel() {

    private val _eventDetailsState: MutableStateFlow<EventDetailsState> =
        MutableStateFlow(EventDetailsState.Loading)
    val eventDetailsState = _eventDetailsState.asStateFlow()

    fun getEventDetails(eventId: String) {
        viewModelScope.launch {
            _eventDetailsState.value = EventDetailsState.Loading
            when (val getEventDetailsResult =
                getEventDetailsUseCase.getEventDetails(eventId = eventId)) {
                is GetEventDetailsUseCaseResult.Error -> {
                    _eventDetailsState.value = EventDetailsState.Error
                }
                is GetEventDetailsUseCaseResult.Success -> {
                    _eventDetailsState.value =
                        EventDetailsState.Success(eventView = getEventDetailsResult.eventView)
                }
            }
        }
    }
}