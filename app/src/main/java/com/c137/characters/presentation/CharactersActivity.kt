package com.c137.characters.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.c137.R
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersActivity : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        viewModel.getCharacters()
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}