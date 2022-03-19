package com.example.marveltestapp.data.network.model

import androidx.room.Ignore
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultDto(
    @Ignore
    val comics: Comics,
    val description: String,
    val events: Events,
    @SerializedName("id")
    @Expose
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)