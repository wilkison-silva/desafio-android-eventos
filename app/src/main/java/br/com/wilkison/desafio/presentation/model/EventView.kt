package br.com.wilkison.desafio.presentation.model

data class EventView(
    val date: Int,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String,
    val id: String
)