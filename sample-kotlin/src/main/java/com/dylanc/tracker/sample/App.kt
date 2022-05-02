package com.dylanc.tracker.sample

import android.app.Application
import android.content.Context
import android.util.Log
import com.dylanc.tracker.TrackHandler
import com.dylanc.tracker.initTracker
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    UMConfigure.setLogEnabled(BuildConfig.DEBUG)
    UMConfigure.preInit(this, "626ca3f630a4f67780c223e6", "Umeng")
    initTracker(this, UMTrackHandler())
  }

  class UMTrackHandler : TrackHandler {
    override fun onEvent(context: Context, eventId: String, params: Map<String, Any>) {
      if (BuildConfig.DEBUG) Log.d("Tracker", "onEvent: eventId = $eventId, params = $params")
      MobclickAgent.onEventObject(context, eventId, params)
    }
  }
}