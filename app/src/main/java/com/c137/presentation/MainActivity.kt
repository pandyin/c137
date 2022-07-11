package com.c137.presentation

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.c137.R
import com.c137.databinding.ActivityMainBinding
import com.c137.presentation.service.MainServiceBinder
import com.c137.presentation.service.MainServiceIntent
import com.c137.presentation.viewmodel.MainViewModel
import com.c137.presentation.viewmodel.Response
import com.google.gson.Gson
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : RxAppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var viewBinding: ActivityMainBinding

    private lateinit var serviceConnection: ServiceConnection

    private var binder: MainServiceBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        ContextCompat.startForegroundService(
            this,
            MainServiceIntent(this)
        )
    }

    override fun onStart() {
        super.onStart()
        subscribeToListOfCharacters()
        bindService()
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
            }
            .subscribe()
    }

    private fun bindService() {
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                binder = service as MainServiceBinder
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                binder = null
            }
        }

        bindService(
            MainServiceIntent(this),
            serviceConnection,
            BIND_AUTO_CREATE
        )
    }
}