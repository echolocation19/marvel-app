package com.example.marveltestapp.data.mapper

import android.util.Log
import com.example.marveltestapp.data.database.CharacterDbModel
import com.example.marveltestapp.data.network.model.CharactersContainerDto
import com.example.marveltestapp.data.network.model.ResultDto
import com.example.marveltestapp.domain.Character

class CharacterMapper {

    fun mapDtoToDbModel(dto: ResultDto): CharacterDbModel =
        CharacterDbModel(
            name = dto.name,
            characterId = dto.characterId,
            modified = dto.modified
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
}