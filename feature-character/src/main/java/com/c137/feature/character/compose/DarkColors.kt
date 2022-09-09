package com.c137.feature.character.compose

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

private val Primary = Color(0xff263238)
private val PrimaryLight = Color(0xff4f5b62)
private val PrimaryDark = Color(0xff000a12)

private val Error = Color(0xFFB00020)

val darkColors = darkColors(
    primary = Primary,
    primaryVariant = Primary,
    secondary = Primary,
    secondaryVariant = Primary,
    background = PrimaryDark,
    surface = PrimaryLight,
    error = Error,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.White
)
