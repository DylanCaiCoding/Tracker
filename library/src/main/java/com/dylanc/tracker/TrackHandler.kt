package com.dylanc.tracker

import android.content.Context

fun interface TrackHandler {
  fun onEvent(context: Context, eventId: String, params: Map<String, String>)
}