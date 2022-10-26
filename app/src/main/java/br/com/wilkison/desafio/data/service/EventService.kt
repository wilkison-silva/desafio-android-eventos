package br.com.wilkison.desafio.data.service

import br.com.wilkison.desafio.data.model.CheckInDataRequest
import br.com.wilkison.desafio.data.model.CheckInDataResponse
import br.com.wilkison.desafio.data.model.EventData
import br.com.wilkison.desafio.data.model.ShortEventData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventService {

    @GET("events")
    suspend fun getEvents(): Response<List<ShortEventData>>

    @GET("events/{id}")
    suspend fun getEventDetails(
        @Path("id") eventId: String,
    ): Response<EventData>

    @POST("checkin")
    suspend fun sendCheckInInfo(
        @Body checkInDataRequest: CheckInDataRequest
    ): Response<CheckInDataResponse>

}