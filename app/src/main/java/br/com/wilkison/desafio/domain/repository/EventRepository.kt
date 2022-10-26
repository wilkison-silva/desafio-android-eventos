package br.com.wilkison.desafio.domain.repository

import br.com.wilkison.desafio.domain.repository.results.GetEventDetailsResults
import br.com.wilkison.desafio.domain.repository.results.GetEventsResult
import br.com.wilkison.desafio.domain.repository.results.SendCheckInInfoResults

interface EventRepository {

    suspend fun getEvents(): GetEventsResult

    suspend fun getEventDetails(eventId: String): GetEventDetailsResults

    suspend fun sendCheckInInfo(eventId: String, name: String, email: String): SendCheckInInfoResults

}