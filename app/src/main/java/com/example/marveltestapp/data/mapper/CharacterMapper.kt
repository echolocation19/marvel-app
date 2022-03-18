package com.example.marveltestapp.data.mapper

import com.example.marveltestapp.data.database.CharacterDbModel
import com.example.marveltestapp.data.network.model.CharactersContainerDto
import com.example.marveltestapp.data.network.model.ResultDto
import com.example.marveltestapp.domain.Character
import java.lang.StringBuilder
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CharacterMapper {

    fun mapDtoToDbModel(dto: ResultDto): CharacterDbModel =
        CharacterDbModel(
            name = dto.name,
            characterId = dto.characterId,
            modified = convertStringToTimestamp(dto.modified)
        )

    fun mapDbModelToEntity(dbModel: CharacterDbModel): Character =
        Character(
            name = dbModel.name,
            characterId = dbModel.characterId,
            modified = dbModel.modified
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

    private fun convertStringToTimestamp(date: String): String {
        val inputFormatter =
            DateTimeFormatter.ofPattern(INPUT_DATE_TIME_FORMAT, Locale.ENGLISH)
        val outputFormatter = DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT, Locale.ENGLISH)
        val mDate = LocalDate.parse(date, inputFormatter)
        return StringBuilder()
            .append("Last updated: ")
            .append(outputFormatter.format(mDate)).toString()
    }

    companion object {
        private const val INPUT_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
        private const val OUTPUT_DATE_TIME_FORMAT = "dd-MM-yyy"
    }

}