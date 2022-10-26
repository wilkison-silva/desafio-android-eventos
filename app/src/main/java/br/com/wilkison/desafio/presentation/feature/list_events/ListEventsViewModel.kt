package br.com.wilkison.desafio.presentation.feature.list_events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wilkison.desafio.domain.usecases.get_events_list.GetEventsListUseCase
import br.com.wilkison.desafio.domain.usecases.get_events_list.GetEventsListUseCaseResult
import br.com.wilkison.desafio.presentation.feature.list_events.states.ListEventsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListEventsViewModel(
    private val getEventsListUseCase: GetEventsListUseCase
) : ViewModel() {

    private val _listEventsState: MutableStateFlow<ListEventsState> =
        MutableStateFlow(ListEventsState.Loading)
    val listEventsState = _listEventsState.asStateFlow()

    fun getEventsList() {
        viewModelScope.launch {
            _listEventsState.value = ListEventsState.Loading
            when (val getEventsListResult = getEventsListUseCase.getEventsList()) {
                is GetEventsListUseCaseResult.Error -> {
                    _listEventsState.value = ListEventsState.Error
                }
                is GetEventsListUseCaseResult.Success -> {
                    _listEventsState.value =
                        ListEventsState.Success(shortEventViewList = getEventsListResult.shortEventViewList)
                }
            }
        }
    }
}
