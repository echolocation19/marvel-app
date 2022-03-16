package com.example.marveltestapp.data.network.model

data class EventsDto(
    val available: String,
    val collectionURI: String,
    val items: List<ItemDto>,
    val returned: String
)