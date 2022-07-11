package com.c137.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c137.data.model.Character
import com.c137.data.model.Status
import com.c137.presentation.api.GetCharacterByIdUseCase
import com.c137.presentation.api.GetCharactersByStatusUseCase
import com.c137.presentation.api.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.flow.collect
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
                    .collect { subject.onNext(Response.Next(it)) }
            } catch (e: Exception) {
                subject.onNext(Response.Error(e.message))
            }
        }

        characterByIdMap[id] = subject
        return subject.toFlowable(BackpressureStrategy.BUFFER)
            .distinctUntilChanged()
    }

    fun getCharacters(): Flowable<List<Character>> {
        return getCharactersUseCase.execute()
    }

    fun getCharactersByStatus(status: Status): Flowable<List<Character>> {
        return getCharactersByStatusUseCase.execute(status)
    }
}

sealed class Response(val isSuccessful: Boolean) {

    data class Next<T>(val item: T) : Response(true)
    data class Error(val errorMessage: String?) : Response(false)
}