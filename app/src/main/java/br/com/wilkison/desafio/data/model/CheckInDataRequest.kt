package br.com.wilkison.desafio.data.model

data class CheckInDataRequest(
    val eventId: String,
    val name: String,
    val email: String
)