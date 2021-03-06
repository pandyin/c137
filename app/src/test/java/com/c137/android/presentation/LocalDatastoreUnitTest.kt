package com.c137.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.c137.data.C137Database
import com.c137.RxTrampolineSchedulerRule
import com.c137.data.model.CharacterData
import com.c137.data.model.CharacterStatus
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*

@RunWith(RobolectricTestRunner::class)
class LocalDatastoreUnitTest {

    private lateinit var db: com.c137.data.C137Database

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxTrampolineSchedulerRule = RxTrampolineSchedulerRule()

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, com.c137.data.C137Database::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun get_characters() {
        val expectedCharacters = listOf(CharacterData(UUID.randomUUID().hashCode(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            CharacterStatus.Alive
        ))

        val dao = db.characterDao()
        dao.insertCharacters(expectedCharacters)
            .andThen(dao.getCharacters())
            .test()
            .assertValue { it == expectedCharacters }
    }
}