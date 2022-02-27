package com.c137.character.domain

import com.c137.character.data.model.LocationWithResidents
import com.c137.character.data.repository.CharacterRepository
import io.reactivex.rxjava3.core.Flowable

class GetLocationUseCaseImpl(private val repository: CharacterRepository) : GetLocationUseCase {

    override fun execute(page: Int): Flowable<List<LocationWithResidents>> {
        return repository.getLocations(page)
    }
}