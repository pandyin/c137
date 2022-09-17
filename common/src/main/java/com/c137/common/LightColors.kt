package com.c137.common

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

private val Primary = Color(0xfffafafa)
private val PrimaryLight = Color(0xffffffff)
private val PrimaryDark = Color(0xffc6c6c6)

private val PrimaryText = Color(0xff555555)
private val SecondaryText = Color(0xffc4c4c4)
private val Error = Color(0xffb00020)

val lightColors = darkColors(
    primary = Primary,
    primaryVariant = Primary,
    secondary = Primary,
    secondaryVariant = Primary,
    background = PrimaryDark,
    surface = PrimaryLight,
    error = Error,
    onPrimary = PrimaryText,
    onSecondary = PrimaryText,
    onBackground = SecondaryText,
    onSurface = PrimaryText,
    onError = Color.White
)
