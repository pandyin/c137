package com.c137.character.presentation

import android.os.Bundle
import com.c137.databinding.ActivityCharactersBinding
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterActivity : RxAppCompatActivity() {

    private val viewModel by viewModel<CharacterViewModel>()

    private lateinit var binding: ActivityCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
    }
}