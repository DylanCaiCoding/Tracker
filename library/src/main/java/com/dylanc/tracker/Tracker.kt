@file:Suppress("unused")

package com.dylanc.tracker

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import java.io.Serializable

internal const val KEY_TRACK_PARAMS = "track_params"
private const val TAG = "Tracker"
private var debug = false
private var trackDispatcher: Array<out TrackDispatcher>? = null

fun initTracker(debug: Boolean, vararg dispatcher: TrackDispatcher) {
  com.dylanc.tracker.debug = debug
  trackDispatcher = dispatcher
}

var Activity.trackNode: TrackNode?
  get() = window.decorView.trackNode
  set(value) {
    window.decorView.trackNode = value
  }

var Fragment.trackNode: TrackNode?
  get() = view?.trackNode
  set(value) {
    view?.trackNode = value
  }

var View.trackNode: TrackNode?
  get() = getTag(R.id.tag_track_node) as? TrackNode
  set(value) {
    setTag(R.id.tag_track_node, value)
  }

fun Activity.postTrack(eventName: String) = window.decorView.postTrack(eventName)

fun Fragment.postTrack(eventName: String) = view?.postTrack(eventName)

fun View.postTrack(eventId: String) {
  trackDispatcher?.forEach {
    val params = collectTrack()
    it.postTrack(eventId, params)
    if (debug) Log.d(TAG, "eventId = $eventId, params = $params")
  }
}

fun Intent.putTrackNode(activity: Activity) = putTrackNode(activity.window.decorView)

fun Intent.putTrackNode(fragment: Fragment) = putTrackNode(fragment.view)

fun Intent.putTrackNode(view: View?) =
  putExtra(KEY_TRACK_PARAMS, view?.collectTrack() as? Serializable)

fun View.collectTrack(): Map<String, Any?> {
  var view: View? = this
  val params = TrackParams()
  while (view != null) {
    val trackNode = view.trackNode
    trackNode?.apply { params.fillTackParams() }
    view = view.parent as? View
  }
  return params.toMap()
}