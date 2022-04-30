@file:Suppress("unused")

package com.dylanc.tracker

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import java.io.Serializable

internal const val KEY_TRACK_PARAMS = "track_params"
private lateinit var application: Application
private var trackHandler: TrackHandler? = null

fun initTracker(application: Application, handler: TrackHandler) {
  com.dylanc.tracker.application = application
  trackHandler = handler
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

fun View.postTrack(eventId: String) =
  trackHandler?.onEvent(application, eventId, collectTrack())

fun Intent.putTrack(activity: Activity): Intent = putTrack(activity.window.decorView)

fun Intent.putTrack(fragment: Fragment): Intent = putTrack(fragment.view)

fun Intent.putTrack(view: View?): Intent =
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