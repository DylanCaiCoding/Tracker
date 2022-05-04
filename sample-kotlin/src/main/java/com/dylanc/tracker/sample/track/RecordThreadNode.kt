package com.dylanc.tracker.sample.track

import com.dylanc.tracker.TrackNode
import com.dylanc.tracker.TrackParams
import java.io.Serializable

/**
 * @author Dylan Cai
 */
class RecordThreadNode : TrackNode, Serializable {
  var isRecord = false

  override fun TrackParams.fillTackParams() {
    put("is_record", isRecord)
  }
}