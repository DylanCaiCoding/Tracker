@file:Suppress("unused")

package com.dylanc.tracker

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

private const val KEY_TRACK_PARAMS = "track_params"

var trackDispatcher: TrackDispatcher? = null

@Suppress("UNCHECKED_CAST")
fun Activity.fillTrack(block: (TrackParams.() -> Unit)? = null) {
  window.decorView.fillTrack {
    val params = intent.getSerializableExtra(KEY_TRACK_PARAMS) as? Map<String, Any?>
    params?.let { putAll(it) }
    block?.invoke(this)
  }
}

fun Fragment.fillTrack(block: TrackParams.() -> Unit) =
  view?.fillTrack(block)

fun RecyclerView.ViewHolder.fillTrack(block: TrackParams.() -> Unit) =
  itemView.fillTrack(block)

fun View.fillTrack(block: TrackParams.() -> Unit) {
  trackNode = TrackNode(block)
}

fun Activity.postTrack(eventName: String) = window.decorView.postTrack(eventName)

fun Fragment.postTrack(eventName: String) = view?.postTrack(eventName)

fun RecyclerView.ViewHolder.postTrack(eventName: String) = itemView.postTrack(eventName)

fun View.postTrack(eventId: String) {
  trackDispatcher?.postTrack(eventId, collectTrack())
}

fun Intent.setTrack(activity: Activity) = setTrack(activity.window.decorView)

fun Intent.setTrack(fragment: Fragment) = setTrack(fragment.view)

fun Intent.setTrack(viewHolder: RecyclerView.ViewHolder) = setTrack(viewHolder.itemView)

fun Intent.setTrack(view: View?) = apply {
  putExtra(KEY_TRACK_PARAMS, view?.collectTrack() as? Serializable)
}

fun View.collectTrack(): Map<String, Any?> {
  var view: View? = this
  val params = TrackParams()
  while (view != null) {
    val trackNode = view.trackNode
    trackNode?.fillTackParams?.invoke(params)
    view = view.parent as? View
  }
  return params.toMap()
}
