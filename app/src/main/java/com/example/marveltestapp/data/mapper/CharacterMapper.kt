package com.example.marveltestapp.data.mapper

import com.example.marveltestapp.data.database.entity.CharacterDbModel
import com.example.marveltestapp.data.database.entity.CharacterInfoDbModel
import com.example.marveltestapp.data.network.model.CharactersContainerDto
import com.example.marveltestapp.data.network.model.Item
import com.example.marveltestapp.data.network.model.ResultDto
import com.example.marveltestapp.data.network.model.Thumbnail
import com.example.marveltestapp.domain.Character
import com.example.marveltestapp.domain.CharacterInfo
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CharacterMapper {

    fun mapDtoToDbModel(dto: ResultDto): CharacterDbModel =
        CharacterDbModel(
            name = dto.name,
            id = dto.id,
            modified = convertStringToTimestamp(dto.modified),
            thumbnail = provideThumbnail(dto.thumbnail),
            comicsUri = provideComicsName(dto.comics.items)
        )

    fun mapDtoToDbInfoModel(dto: ResultDto): CharacterInfoDbModel =
        CharacterInfoDbModel(
            name = dto.name,
            id = dto.id,
            modified = convertStringToTimestamp(dto.modified),
            thumbnail = provideThumbnail(dto.thumbnail),
            comicsUri = provideComicsName(dto.comics.items)
        )

    fun mapDbModelToEntity(dbModel: CharacterDbModel): Character =
        Character(
            name = dbModel.name,
            id = dbModel.id,
            modified = dbModel.modified,
            thumbnail = dbModel.thumbnail
        )

    fun mapDbInfoModelToEntity(dbModel: CharacterInfoDbModel): CharacterInfo =
        CharacterInfo(
            name = dbModel.name,
            id = dbModel.id,
            modified = dbModel.modified,
            thumbnail = dbModel.thumbnail,
            comicsUri = dbModel.comicsUri
        )


    fun mapCharactersContainerToListResult(container: CharactersContainerDto)
            : List<ResultDto> {
        val result = mutableListOf<ResultDto>()
        val resultList = container.data.results
        for (res in resultList) {
            result.add(res)
        }
        return result
    }

    private fun provideComicsName(items: List<Item>): List<String> {
        val namesList = mutableListOf<String>()
        items.forEach {
            namesList.add(it.name)
        }
        return namesList
    }

    private fun convertStringToTimestamp(date: String): String {
        val inputFormatter =
            DateTimeFormatter.ofPattern(INPUT_DATE_TIME_FORMAT, Locale.ENGLISH)
        val outputFormatter = DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT, Locale.ENGLISH)
        val mDate = LocalDate.parse(date, inputFormatter)
        return outputFormatter.format(mDate)
    }

    private fun provideThumbnail(thumbnail: Thumbnail): String {
        val refactoredThumbnail = StringBuilder(thumbnail.path).apply {
            insert(4, "s")
        }.toString()
        return refactoredThumbnail + "." + thumbnail.extension
    }

    companion object {
        private const val INPUT_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
        private const val OUTPUT_DATE_TIME_FORMAT = "dd-MM-yyy"
    }

}