package br.com.wilkison.desafio.domain.usecases.get_events_list

interface GetEventsListUseCase {

    suspend fun getEventsList() : GetEventsListUseCaseResult

}