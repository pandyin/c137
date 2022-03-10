package com.c137.presentation

import androidx.lifecycle.ViewModel
import com.c137.data.model.Character
import com.c137.data.model.Status
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class MainViewModel : ViewModel() {

    private val buttonStateSubject: BehaviorSubject<ViewState> by lazy {
        BehaviorSubject.createDefault(ViewState.Idle)
    }

    val buttonStateFlowable: Flowable<ViewState> by lazy {
        buttonStateSubject.toFlowable(BackpressureStrategy.BUFFER)
    }

    abstract fun getCharactersByStatus(page: Int, status: Status): Flowable<List<Character>>

    abstract fun getCharacters(page: Int): Flowable<List<Character>>

    abstract fun getCharacterById(id: Int): Flowable<Response>
}