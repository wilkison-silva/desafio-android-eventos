package br.com.wilkison.desafio.domain.usecases.get_event_details

interface GetEventDetailsUseCase {

    suspend fun getEventDetails(eventId: String) : GetEventDetailsUseCaseResult

}