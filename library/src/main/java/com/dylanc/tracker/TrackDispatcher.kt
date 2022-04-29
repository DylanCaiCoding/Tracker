package com.dylanc.tracker

fun interface TrackDispatcher {
  fun postTrack(eventId: String, params: Map<String, Any?>)
}