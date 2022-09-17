package com.c137.common

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

private val Primary = Color(0xff263238)
private val PrimaryLight = Color(0xff4f5b62)
private val PrimaryDark = Color(0xff000a12)

private val PrimaryText = Color.White
private val SecondaryText = Color(0xff9d9d9d)
private val Error = Color(0xffb00020)

val darkColors = darkColors(
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
