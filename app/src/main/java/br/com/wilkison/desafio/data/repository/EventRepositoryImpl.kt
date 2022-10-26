package br.com.wilkison.desafio.data.repository

import br.com.wilkison.desafio.data.service.EventsService
import br.com.wilkison.desafio.domain.repository.EventRepository
import br.com.wilkison.desafio.domain.repository.results.GetEventDetailsResults
import br.com.wilkison.desafio.domain.repository.results.GetEventsResult

class EventRepositoryImpl(
    private val eventsService: EventsService
) : EventRepository {

    override suspend fun getEvents(): GetEventsResult {
        try {
            val response = eventsService.getEvents()
            if (response.isSuccessful) {
                response.body()?.let { eventDataList ->
                    val eventDomainList = eventDataList.map { it.convertToEventDomain() }
                    return GetEventsResult.Success(eventDomainList = eventDomainList)
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return GetEventsResult.Error
    }

    override suspend fun getEventDetails(eventId: String): GetEventDetailsResults {
        try {
            val response = eventsService.getEventDetails(eventId = eventId)
            if (response.isSuccessful) {
                response.body()?.let { eventData ->
                    val eventDomain = eventData.convertToEventDomain()
                    return GetEventDetailsResults.Success(eventDomain = eventDomain)
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return GetEventDetailsResults.Error
    }
}