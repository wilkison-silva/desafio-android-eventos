package br.com.wilkison.desafio.data.repository

import br.com.wilkison.desafio.data.model.CheckInDataRequest
import br.com.wilkison.desafio.data.service.EventService
import br.com.wilkison.desafio.domain.repository.EventRepository
import br.com.wilkison.desafio.domain.repository.results.GetEventDetailsResults
import br.com.wilkison.desafio.domain.repository.results.GetEventsResult
import br.com.wilkison.desafio.domain.repository.results.SendCheckInInfoResults

class EventRepositoryImpl(
    private val eventService: EventService
) : EventRepository {

    override suspend fun getEvents(): GetEventsResult {
        try {
            val response = eventService.getEvents()
            if (response.isSuccessful) {
                response.body()?.let { shortEventDataList ->
                    val shortEventDomainList =
                        shortEventDataList.map { it.convertToShortEventDomain() }
                    return GetEventsResult.Success(shortEventDomainList = shortEventDomainList)
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return GetEventsResult.Error
    }

    override suspend fun getEventDetails(eventId: String): GetEventDetailsResults {
        try {
            val response = eventService.getEventDetails(eventId = eventId)
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

    override suspend fun sendCheckInInfo(
        eventId: String,
        name: String,
        email: String
    ): SendCheckInInfoResults {
        try {
            val response = eventService.sendCheckInInfo(
                CheckInDataRequest(
                    eventId = eventId,
                    name = name,
                    email = email
                )
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    return SendCheckInInfoResults.Success
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return SendCheckInInfoResults.Error
    }
}