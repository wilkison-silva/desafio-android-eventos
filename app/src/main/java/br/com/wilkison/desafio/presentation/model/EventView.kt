package br.com.wilkison.desafio.presentation.model

data class EventView(
    val date: String,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: String,
    val title: String,
    val id: String
)