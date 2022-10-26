package br.com.wilkison.desafio.domain.usecases.get_event_details

import br.com.wilkison.desafio.domain.repository.EventRepository
import br.com.wilkison.desafio.domain.repository.results.GetEventDetailsResults

class GetEventDetailsUseCaseImpl(
    private val eventRepository: EventRepository
) : GetEventDetailsUseCase {

    override suspend fun getEventDetails(eventId: String): GetEventDetailsUseCaseResult {
        return when(val result = eventRepository.getEventDetails(eventId = eventId)) {
            is GetEventDetailsResults.Error -> {
                GetEventDetailsUseCaseResult.Error
            }
            is GetEventDetailsResults.Success -> {
                val eventView = result.eventDomain.convertToEventView()
                GetEventDetailsUseCaseResult.Success(eventView = eventView)
            }
        }
    }

}