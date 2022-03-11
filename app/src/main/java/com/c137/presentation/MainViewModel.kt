package com.c137.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c137.data.model.Character
import com.c137.data.model.Status
import com.c137.domain.GetCharacterByIdUseCase
import com.c137.domain.GetCharactersByStatusUseCase
import com.c137.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharactersByStatusUseCase: GetCharactersByStatusUseCase,
) : ViewModel() {

    private val characterByIdMap: MutableMap<Int, BehaviorSubject<Response>> = mutableMapOf()

    fun getCharacterById(id: Int): Flowable<Response> {
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

    fun getCharacters(page: Int): Flowable<List<Character>> {
        return getCharactersUseCase.execute(page)
    }

    fun getCharactersByStatus(page: Int, status: Status): Flowable<List<Character>> {
        return getCharactersByStatusUseCase.execute(page, status)
    }
}

sealed class Response(val isSuccessful: Boolean) {

    class Next<T>(val item: T) : Response(true)
    class Error(val exception: Exception) : Response(false)
}