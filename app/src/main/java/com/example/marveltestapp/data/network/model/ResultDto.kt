package com.example.marveltestapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ResultDto(
    val comics: ComicsDto,
    val description: String,
    val events: EventsDto,
    @SerializedName("id")
    val characterId: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: SeriesDto,
    val stories: StoriesDto,
    val thumbnail: ThumbnailDto,
    val urls: List<UrlDto>
)