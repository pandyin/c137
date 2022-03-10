package com.c137.presentation

import androidx.lifecycle.viewModelScope
import com.c137.data.model.Character
import com.c137.data.model.Status
import com.c137.domain.GetCharacterByIdUseCase
import com.c137.domain.GetCharactersByStatusUseCase
import com.c137.domain.GetCharactersUseCase
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class MainViewModelImpl(
    private val getCharactersByStatusUseCase: GetCharactersByStatusUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
) : MainViewModel() {

    private val characterByIdMap: MutableMap<Int, BehaviorSubject<Response>> = mutableMapOf()

    override fun getCharactersByStatus(page: Int, status: Status): Flowable<List<Character>> {
        return getCharactersByStatusUseCase.execute(page, status)
    }

    override fun getCharacters(page: Int): Flowable<List<Character>> {
        return getCharactersUseCase.execute(page)
    }

    override fun getCharacterById(id: Int): Flowable<Response> {
        characterByIdMap[id]?.let {
            return it.toFlowable(BackpressureStrategy.BUFFER)
        }

        val subject = BehaviorSubject.create<Response>()
        viewModelScope.launch {
            try {
                getCharacterByIdUseCase.execute(id)
                    .onCompletion { subject.onComplete() }
                    .collect { subject.onNext(Response.Next(it)) }
            } catch (e: Exception) {
                subject.onNext(Response.Error(e))
            }
        }

        characterByIdMap[id] = subject
        return subject.toFlowable(BackpressureStrategy.BUFFER)
    }
}

sealed class Response {

    class Next<T>(val t: T) : Response() {
        override fun isSuccessful() = true
    }

    class Error(val exception: Exception) : Response() {
        override fun isSuccessful() = false
    }

    abstract fun isSuccessful(): Boolean
}