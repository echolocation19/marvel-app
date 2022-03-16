package com.example.marveltestapp.data.network.model

data class DataDto(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<ResultDto>,
    val total: String
)