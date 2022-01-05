package com.c137.characters.mockist.di

import com.c137.characters.data.model.Character
import com.c137.characters.data.repository.datastore.local.api.CharactersDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import org.koin.dsl.module

val stubDatabaseModule = module {
    single<CharactersDao> {
        object : CharactersDao {
            override fun insertCharacters(characters: List<Character>): Completable {
                TODO("Not yet implemented")
            }

            override fun getCharacters(): Flowable<List<Character>> {
                TODO("Not yet implemented")
            }
        }
    }
}