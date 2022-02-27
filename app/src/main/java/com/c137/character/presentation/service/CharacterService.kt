package com.c137.character.presentation.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.IMPORTANCE_DEFAULT
import com.c137.character.data.model.Character
import com.c137.character.domain.GetCharactersUseCase
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.koin.android.ext.android.inject

private const val DIMENSION = "c137"

class CharacterService : Service() {

    private val characterSubject: BehaviorSubject<Character> = BehaviorSubject.create()
    private val binder = CharacterServiceBinder(characterSubject)

    private val useCase: GetCharactersUseCase by inject()
    private var disposable: Disposable? = null

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        subscribeToStreamOfCharacters()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(startId)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    private fun subscribeToStreamOfCharacters() {
        disposable?.dispose()
        disposable = Flowable.range(1, 1000)
            .flatMapSingle { useCase.execute(it) }
            .flatMapIterable { it }
            .doOnNext {
                Thread.sleep(1000)
                characterSubject.onNext(it)
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
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