package com.c137.domain.usecase.model.mapper

import com.c137.domain.usecase.model.BaseDomainModel
import com.c137.presentation.model.BasePresentationModel

interface DataMapper<DOMAIN : BaseDomainModel, BasePresentation : BasePresentationModel> {

    fun map(domain: DOMAIN): BasePresentation
}