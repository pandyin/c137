package com.c137.characters.data.repository.datastore.local

import com.c137.characters.data.repository.datastore.local.api.CharactersDao
import org.jetbrains.annotations.TestOnly

class CharactersLocalDatastoreImpl(private val charactersDao: CharactersDao) : CharactersLocalDatastore {

    @TestOnly
    constructor() : this(object : CharactersDao {

    })
}