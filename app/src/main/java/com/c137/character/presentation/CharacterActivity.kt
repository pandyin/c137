package com.c137.character.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.c137.R
import com.c137.character.presentation.service.CharacterService
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterActivity : AppCompatActivity() {

    private val viewModel by viewModel<CharacterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        val intent = Intent(this, CharacterService::class.java)
        ContextCompat.startForegroundService(this, intent)
    }

    override fun onStart() {
        super.onStart()
    }
}