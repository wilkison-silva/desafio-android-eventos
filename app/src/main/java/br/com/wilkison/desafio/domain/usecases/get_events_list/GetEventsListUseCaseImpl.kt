package br.com.wilkison.desafio.domain.usecases.get_events_list

import br.com.wilkison.desafio.domain.repository.EventRepository
import br.com.wilkison.desafio.domain.repository.results.GetEventsResult

class GetEventsListUseCaseImpl(
    private val eventRepository: EventRepository
) : GetEventsListUseCase {
    override suspend fun getEventsList(): GetEventsListUseCaseResult {
        return when(val result = eventRepository.getEvents()) {
            is GetEventsResult.Error -> {
                GetEventsListUseCaseResult.Error
            }
            is GetEventsResult.Success -> {
                val eventViewList = result.eventDomainList.map { it.convertToEventView() }
                GetEventsListUseCaseResult.Success(eventViewList = eventViewList)
            }
        }
    }
}