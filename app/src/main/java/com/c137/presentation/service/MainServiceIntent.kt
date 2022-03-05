package com.c137.presentation.service

import android.content.Context
import android.content.Intent

class MainServiceIntent(context: Context) : Intent(context, CharacterService::class.java)