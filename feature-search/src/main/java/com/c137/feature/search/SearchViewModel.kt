package com.c137.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c137.domain.GetAliveCharactersUseCase
import com.c137.domain.GetCharacterByIdUseCase
import com.c137.domain.GetCharactersUseCase
import com.c137.domain.GetDeadCharactersUseCase
import com.c137.domain.model.PresentationCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getAliveCharactersUseCase: GetAliveCharactersUseCase,
    private val getDeadCharactersUseCase: GetDeadCharactersUseCase
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
                    .collect { subject.onNext(Response.Next(it)) }
            } catch (e: Exception) {
                subject.onNext(Response.Error(e.message))
            }
        }

        characterByIdMap[id] = subject
        return subject.toFlowable(BackpressureStrategy.BUFFER)
            .distinctUntilChanged()
    }

    fun getCharacters(): Flowable<List<PresentationCharacter>> {
        return getCharactersUseCase.execute()
    }

    fun getAliveCharacters(): Flowable<List<PresentationCharacter>> {
        return getAliveCharactersUseCase.execute()
    }

    fun getDeadCharacters(): Flowable<List<PresentationCharacter>> {
        return getDeadCharactersUseCase.execute()
    }
}

sealed class Response(val isSuccessful: Boolean) {

    data class Next<T>(val item: T) : Response(true)
    data class Error(val errorMessage: String?) : Response(false)
}