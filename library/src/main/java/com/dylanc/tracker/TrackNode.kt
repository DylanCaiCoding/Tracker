package com.dylanc.tracker

import android.app.Activity

fun Activity.PageTrackNode(keyMap: Map<String, String>, trackNode: TrackNode = TrackNode { }) =
  PageTrackNode(this, keyMap, trackNode)

fun interface TrackNode {
  fun TrackParams.fillTackParams()
}

class PageTrackNode(
  private val activity: Activity,
  private val keyMap: Map<String, String> = emptyMap(),
  private val trackNode: TrackNode = TrackNode { }
) : TrackNode {

  override fun TrackParams.fillTackParams() {
    @Suppress("UNCHECKED_CAST")
    val params = activity.intent.getSerializableExtra(KEY_TRACK_PARAMS) as? Map<String, Any?>
    params?.takeIf { it.isNotEmpty() }?.forEach {
      put(keyMap.getOrElse(it.key) { it.key }, it.value)
    }?.let {
      trackNode.apply { fillTackParams() }
    }
  }
}