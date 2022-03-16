package com.example.marveltestapp.data.network.model

data class ComicsDto(
    val available: String,
    val collectionURI: String,
    val items: List<ItemDto>,
    val returned: String
)