package com.c137.domain.usecase.model.mapper

import com.c137.domain.usecase.model.BaseDomainModel

interface DataMapper<DOMAIN : BaseDomainModel, BasePresentation : com.c137.presentation.model.BasePresentationModel> {

    fun map(domain: DOMAIN): BasePresentation
}