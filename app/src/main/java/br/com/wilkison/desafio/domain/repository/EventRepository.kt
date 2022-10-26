package br.com.wilkison.desafio.domain.repository

import br.com.wilkison.desafio.domain.repository.results.GetEventDetailsResults
import br.com.wilkison.desafio.domain.repository.results.GetEventsResult

interface EventRepository {

    suspend fun getEvents(): GetEventsResult

    suspend fun getEventDetails(eventId: String): GetEventDetailsResults

}