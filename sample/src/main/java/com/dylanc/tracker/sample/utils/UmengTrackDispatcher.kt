package com.dylanc.tracker.sample.utils

import android.util.Log
import com.dylanc.tracker.TrackDispatcher

class UmengTrackDispatcher : TrackDispatcher {

  override fun postTrack(eventId: String, params: Map<String, Any?>) {
    Log.d("Umeng", mapOf("eventName" to eventId, "params" to params).toString())
  }
}