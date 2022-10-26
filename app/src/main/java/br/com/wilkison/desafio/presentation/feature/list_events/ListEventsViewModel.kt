package br.com.wilkison.desafio.presentation.feature.list_events

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wilkison.desafio.domain.usecases.get_events_list.GetEventsListUseCase
import br.com.wilkison.desafio.domain.usecases.get_events_list.GetEventsListUseCaseResult
import kotlinx.coroutines.launch

class ListEventsViewModel(
    private val getEventsListUseCase: GetEventsListUseCase
) : ViewModel() {


    fun getEventsList() {
        viewModelScope.launch {
            when (val getEventsListResult = getEventsListUseCase.getEventsList()) {
                is GetEventsListUseCaseResult.Error -> {
                    Log.i("Testando", "erro ao buscar")
                }
                is GetEventsListUseCaseResult.Success -> {
                    getEventsListResult.shortEventViewList.forEach {
                        Log.i("Testando", "getEventsList: $it")
                    }
                }
            }
        }
    }
}