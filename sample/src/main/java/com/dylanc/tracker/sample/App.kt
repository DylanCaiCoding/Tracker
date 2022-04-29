package com.dylanc.tracker.sample

import android.app.Application
import com.dylanc.tracker.sample.utils.UmengTrackDispatcher
import com.dylanc.tracker.trackDispatcher

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    trackDispatcher = UmengTrackDispatcher()
  }
}