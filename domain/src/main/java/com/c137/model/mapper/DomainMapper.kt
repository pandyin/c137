package com.c137.model.mapper

import com.c137.model.BaseDomainModel
import com.c137.model.BasePresentationModel

interface DataMapper<DOMAIN : BaseDomainModel, BasePresentation : BasePresentationModel> {

    fun map(domain: DOMAIN): BasePresentation
}