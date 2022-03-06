package com.c137.presentation.di.dagger

import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module(subcomponents = [MainActivityComponent::class])
class MainActivityModule