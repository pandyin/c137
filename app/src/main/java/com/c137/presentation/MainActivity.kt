package com.c137.presentation

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.c137.C137App
import com.c137.databinding.ActivityMainBinding
import com.c137.presentation.di.dagger.DaggerMainActivityComponent
import com.c137.presentation.service.MainServiceBinder
import com.c137.presentation.service.MainServiceIntent

class MainActivity : BaseActivity<ActivityMainBinding>() {

//    private val viewModel by viewModel<MainViewModel>()

    private lateinit var serviceConnection: ServiceConnection

    private var binder: MainServiceBinder? = null

    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMainActivityComponent
            .factory()
            .create((application as C137App).appComponent)
            .inject(this)

        ContextCompat.startForegroundService(
            this,
            MainServiceIntent(this)
        )
    }

    override fun onStart() {
        super.onStart()
        bindToViewState()
        bindService()
    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
    }

    private fun bindToViewState() {
//        viewModel.buttonStateFlowable
//            .doOnNext {
//                binding.button.text = it.name
//                binding.button.isEnabled = it.enabled
//            }
//            .bindToLifecycle(this)
//            .subscribe()
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