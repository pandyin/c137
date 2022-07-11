package com.c137.data.model.mapper

import com.c137.data.model.BaseDataModel
import com.c137.domain.usecase.model.BaseDomainModel

interface DataMapper<DATA : BaseDataModel, DOMAIN : BaseDomainModel> {

    fun map(data: DATA): DOMAIN
}