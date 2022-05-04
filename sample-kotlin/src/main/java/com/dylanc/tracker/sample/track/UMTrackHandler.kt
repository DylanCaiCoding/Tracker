package com.dylanc.tracker.sample.track

import android.content.Context
import android.util.Log
import com.dylanc.tracker.TrackHandler
import com.dylanc.tracker.sample.BuildConfig
import com.umeng.analytics.MobclickAgent

/**
 * @author Dylan Cai
 */
class UMTrackHandler : TrackHandler {
  override fun onEvent(context: Context, eventId: String, params: Map<String, String>) {
    if (BuildConfig.DEBUG) Log.d("Tracker", "onEvent: eventId = $eventId, params = $params")
    MobclickAgent.onEvent(context, eventId, params)
  }
}