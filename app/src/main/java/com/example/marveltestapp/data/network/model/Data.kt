package com.example.marveltestapp.data.network.model

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultDto>,
    val total: Int
)