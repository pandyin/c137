package com.c137.character.presentation.service

import android.os.Binder
import com.c137.character.data.model.Character
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CharacterServiceBinder(private val characterSubject: BehaviorSubject<Character>) :
    Binder() {

    fun characterFlowable(): Flowable<Character> =
        characterSubject.toFlowable(BackpressureStrategy.BUFFER)
}