package com.c137.di

import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastore
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastoreImpl
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.koin.dsl.module

val mockCharacterRemoteDatastoreModule = module {
    factory<CharactersRemoteDatastore> {
        val mockRemoteDatastore = mockk<CharactersRemoteDatastoreImpl>()
        mockRemoteDatastore
    }
}