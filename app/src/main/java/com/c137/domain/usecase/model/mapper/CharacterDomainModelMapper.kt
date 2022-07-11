package com.c137.domain.usecase.model.mapper

import com.c137.domain.usecase.model.CharacterDomainModel
import com.c137.presentation.model.CharacterPresentationModel

class CharacterDomainModelMapper : DataMapper<CharacterDomainModel, CharacterPresentationModel> {

    override fun map(domain: CharacterDomainModel): CharacterPresentationModel {
        return CharacterPresentationModel(domain.name)
    }
}