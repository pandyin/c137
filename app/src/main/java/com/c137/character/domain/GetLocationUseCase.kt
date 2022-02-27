package com.c137.character.domain

import com.c137.character.data.model.LocationWithResidents
import io.reactivex.rxjava3.core.Flowable

interface GetLocationUseCase {

    fun execute(page: Int): Flowable<List<LocationWithResidents>>
}