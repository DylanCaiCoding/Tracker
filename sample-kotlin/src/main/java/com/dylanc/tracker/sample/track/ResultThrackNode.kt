package com.dylanc.tracker.sample.track

import com.dylanc.tracker.TrackNode
import com.dylanc.tracker.TrackParams

/**
 * @author Dylan Cai
 */
class ResultThrackNode : TrackNode {
  var result: String? = null

  override fun fillTackParams(params: TrackParams) {
    result?.let { params.put("result", it) }
  }
}