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

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dylanc.tracker.TrackNode
import com.dylanc.tracker.sample.R
import com.dylanc.tracker.sample.adapter.VideoAdapter
import com.dylanc.tracker.sample.repository.DataRepository
import com.dylanc.tracker.trackNode

class RecommendFragment : Fragment(R.layout.layout_list) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    trackNode = TrackNode("channel_name" to "recommend")

    val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
    val adapter = VideoAdapter(requireActivity())
    adapter.submitList(DataRepository.recommendVideoList)
    recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    recyclerView.adapter = adapter
  }
}