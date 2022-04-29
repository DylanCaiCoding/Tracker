package com.dylanc.tracker.sample

import android.app.Application
import com.dylanc.tracker.initTracker
import com.dylanc.tracker.sample.utils.UmengTrackDispatcher

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    initTracker(BuildConfig.DEBUG, UmengTrackDispatcher())
  }
}