package com.dylanc.tracker

import android.app.Activity

fun interface TrackNode {
  fun TrackParams.fillTackParams()
}

@Suppress("UNCHECKED_CAST", "FunctionName")
fun Activity.PageTrackNode(keyMap: Map<String, String> = emptyMap(), trackNode: TrackNode = TrackNode { }): TrackNode {
  val params = intent.getSerializableExtra(KEY_TRACK_PARAMS) as? Map<String, Any>
  threadNodeClazz = intent.getStringArrayExtra(KEY_TRACK_THREAD_NODES)?.run { MutableList(size) { Class.forName(get(it)) } }
  return TrackNode {
    params?.forEach {
      put(keyMap.getOrElse(it.key) { it.key }, it.value)
    }?.let {
      trackNode.apply { fillTackParams() }
    }
  }
}