package com.c137.presentation

import android.content.ServiceConnection
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.c137.R
import com.c137.databinding.ActivityMainBinding
import com.c137.feature.search.MainViewModel
import com.c137.feature.search.Response
import com.google.gson.Gson
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : RxAppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var viewBinding: ActivityMainBinding

    private lateinit var serviceConnection: ServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    override fun onStart() {
        super.onStart()
        subscribeToListOfCharacters()
    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
    }

    private fun subscribeToListOfCharacters() {
        viewModel.getCharacterById(3)
            .doOnNext {
                when (it is Response.Error) {
                    true -> {
                        val errorMessage = it.errorMessage ?: getString(R.string.unknown_error)
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    false -> Log.e("testtest", Gson().toJson(it))
                }
            }.subscribe()
    }
}