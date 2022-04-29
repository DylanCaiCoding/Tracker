package com.dylanc.tracker

import android.view.View

class TrackNode(val fillTackParams: TrackParams.() -> Unit)

var View.trackNode: TrackNode?
  get() = getTag(R.id.tag_track_node) as? TrackNode
  set(value) {
    setTag(R.id.tag_track_node, value)
  }