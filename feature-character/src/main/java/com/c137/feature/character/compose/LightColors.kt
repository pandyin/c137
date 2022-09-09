package com.c137.feature.character.compose

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

private val Primary = Color(0xfffafafa)
private val PrimaryLight = Color(0xffffffff)
private val PrimaryDark = Color(0xffc6c6c6)

private val Error = Color(0xFFB00020)

val lightColors = darkColors(
    primary = Primary,
    primaryVariant = Primary,
    secondary = Primary,
    secondaryVariant = Primary,
    background = PrimaryDark,
    surface = PrimaryLight,
    error = Error,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)
