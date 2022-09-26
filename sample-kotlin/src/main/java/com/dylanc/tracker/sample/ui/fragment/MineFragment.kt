/*
 * Copyright (c) 2022. Dylan Cai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dylanc.tracker.sample.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dylanc.tracker.postTrack
import com.dylanc.tracker.putThreadTrackNode
import com.dylanc.tracker.sample.R
import com.dylanc.tracker.sample.track.ResultTrackNode
import com.dylanc.tracker.sample.ui.SignInActivity
import com.dylanc.tracker.updateThreadTrackNode

class MineFragment : Fragment(R.layout.fragment_mine) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.putThreadTrackNode(ResultTrackNode())
    view.findViewById<View>(R.id.btn_sign_in).setOnClickListener {
      it.updateThreadTrackNode<ResultTrackNode> { result = "test" }
      it.postTrack("click_test", ResultTrackNode::class.java)
      startActivity(Intent(requireContext(), SignInActivity::class.java))
    }
  }
}