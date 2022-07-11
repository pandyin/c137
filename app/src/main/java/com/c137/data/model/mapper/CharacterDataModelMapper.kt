package com.c137.data.model.mapper

import com.c137.data.model.CharacterDataModel
import com.c137.domain.usecase.model.CharacterDomainModel

class CharacterDataModelMapper : DataMapper<CharacterDataModel, CharacterDomainModel> {

    override fun map(data: CharacterDataModel): CharacterDomainModel {
        return CharacterDomainModel(data.name)
    }
}