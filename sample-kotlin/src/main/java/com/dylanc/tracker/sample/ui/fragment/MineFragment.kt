package com.dylanc.tracker.sample.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dylanc.tracker.getTrackThreadNode
import com.dylanc.tracker.putTrackThreadNode
import com.dylanc.tracker.sample.R
import com.dylanc.tracker.sample.track.RecordThreadNode

class MineFragment : Fragment(R.layout.fragment_mine) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    requireActivity().putTrackThreadNode(RecordThreadNode())
    getTrackThreadNode<RecordThreadNode>()?.isRecord = true
  }
}