package com.c137.character.presentation

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.c137.character.presentation.service.CharacterServiceBinder
import com.c137.character.presentation.service.CharacterServiceIntent
import com.c137.databinding.ActivityCharactersBinding
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterActivity : RxAppCompatActivity() {

    private val viewModel by viewModel<CharacterViewModel>()

    private lateinit var binding: ActivityCharactersBinding
    private lateinit var serviceConnection: ServiceConnection

    private var binder: CharacterServiceBinder? = null
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ContextCompat.startForegroundService(
            this,
            CharacterServiceIntent(this)
        )
    }

    override fun onStart() {
        super.onStart()
        bindService()
    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
    }

    private fun bindService() {
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                binder = service as CharacterServiceBinder

                disposable?.dispose()
                disposable = subscribeToStreamOfCharacters()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                binder = null

                disposable?.dispose()
                disposable = null
            }
        }

        bindService(
            CharacterServiceIntent(this),
            serviceConnection,
            BIND_AUTO_CREATE
        )
    }

    private fun subscribeToStreamOfCharacters(): Disposable? {
        return null
    }
}