package com.blibli.composesample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication: Application() {
  override fun onCreate() {
    super.onCreate()
  }
}