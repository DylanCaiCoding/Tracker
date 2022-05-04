package com.dylanc.tracker.sample

import android.app.Application
import com.dylanc.tracker.initTracker
import com.dylanc.tracker.sample.track.UMTrackHandler
import com.umeng.commonsdk.UMConfigure

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    UMConfigure.setLogEnabled(BuildConfig.DEBUG)
    UMConfigure.preInit(this, "626ca3f630a4f67780c223e6", "Umeng")
    initTracker(this, UMTrackHandler())
  }
}