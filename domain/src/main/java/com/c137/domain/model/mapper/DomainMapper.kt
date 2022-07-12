package com.c137.domain.model.mapper

import com.c137.domain.model.BaseDomainModel
import com.c137.domain.model.BasePresentationModel

interface DataMapper<DOMAIN : BaseDomainModel, BasePresentation : BasePresentationModel> {

    fun map(domain: DOMAIN): BasePresentation
}