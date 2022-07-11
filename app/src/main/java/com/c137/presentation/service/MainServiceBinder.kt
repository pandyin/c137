package com.c137.presentation.service

import android.os.Binder
import com.c137.data.model.CharacterDataModel
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainServiceBinder(private val characterSubject: BehaviorSubject<CharacterDataModel>) :
    Binder() {

    fun characterFlowable(): Flowable<CharacterDataModel> =
        characterSubject.toFlowable(BackpressureStrategy.BUFFER)
}