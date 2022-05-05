@file:Suppress("unused", "UNCHECKED_CAST", "FunctionName")

package com.dylanc.tracker

import android.app.Activity

fun interface TrackNode {
  fun fillTackParams(params: TrackParams)
}

fun TrackNode(vararg params: Pair<String, String>): TrackNode =
  TrackNode { it.putAll(mapOf(*params)) }

fun Activity.PageTrackNode(vararg params: Pair<String, String>): TrackNode =
  PageTrackNode(emptyMap()) { it.putAll(mapOf(*params)) }

fun Activity.PageTrackNode(referrerKeyMap: Map<String, String>, vararg params: Pair<String, String>): TrackNode =
  PageTrackNode(referrerKeyMap) { it.putAll(mapOf(*params)) }

fun Activity.PageTrackNode(referrerKeyMap: Map<String, String> = emptyMap(), trackNode: TrackNode = TrackNode { }): TrackNode {
  val referrerParams = intent.getSerializableExtra(KEY_TRACK_PARAMS) as? Map<String, Any>
  threadNodeClazz = intent.getStringArrayExtra(KEY_TRACK_THREAD_NODES)?.run { MutableList(size) { Class.forName(get(it)) } }
  return TrackNode { params ->
    referrerParams?.forEach {
      params.put(referrerKeyMap.getOrElse(it.key) { it.key }, it.value)
    }?.let {
      trackNode.fillTackParams(params)
    }
  }
}