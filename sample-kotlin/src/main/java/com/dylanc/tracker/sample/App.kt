package com.dylanc.tracker.sample

import android.app.Application
import com.dylanc.tracker.initTracker
import com.dylanc.tracker.sample.const.UM_CHANNEL
import com.dylanc.tracker.sample.const.UM_APP_ID
import com.dylanc.tracker.sample.track.UMTrackHandler
import com.umeng.commonsdk.UMConfigure

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    UMConfigure.setLogEnabled(BuildConfig.DEBUG)
    UMConfigure.preInit(this, UM_APP_ID, UM_CHANNEL)
    initTracker(this, UMTrackHandler())
  }
}