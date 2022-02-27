package com.c137.character.presentation.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.IMPORTANCE_DEFAULT
import com.c137.character.domain.GetCharactersUseCase
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject

private const val DIMENSION = "c137"

class CharacterService : Service() {

    private var disposable: Disposable? = null

    val useCase: GetCharactersUseCase by inject()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        subscribeToStreamOfCharacters()
        startForeground(startId)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    private fun subscribeToStreamOfCharacters() {
        if (disposable == null) {
            disposable = Flowable.range(1, 1000)
                .flatMapSingle { useCase.execute(it) }
                .flatMapIterable { it }
                .doOnNext { Thread.sleep(1000) }
                .subscribeOn(Schedulers.io())
                .subscribe()
        }
    }

    private fun startForeground(startId: Int) {
        val channel = NotificationChannelCompat.Builder(DIMENSION, IMPORTANCE_DEFAULT)
            .setName(DIMENSION)
            .build()

        NotificationManagerCompat.from(this)
            .createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(this, DIMENSION)
            .setContentTitle(DIMENSION)
            .setContentText(DIMENSION)
            .build()

        startForeground(startId, notification)
    }
}