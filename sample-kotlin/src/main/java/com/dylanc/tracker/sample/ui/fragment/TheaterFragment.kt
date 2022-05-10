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
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dylanc.tracker.TrackNode
import com.dylanc.tracker.sample.R
import com.dylanc.tracker.sample.databinding.FragmentTheaterBinding
import com.dylanc.tracker.trackNode
import com.google.android.material.tabs.TabLayoutMediator

class TheaterFragment : Fragment(R.layout.fragment_theater) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    trackNode = TrackNode("tab_name" to "theater")

    val binding = FragmentTheaterBinding.bind(view)
    val fragments = listOf(RecommendFragment(), MovieFragment())
    binding.viewPager2.adapter = object : FragmentStateAdapter(this) {
      override fun getItemCount() = fragments.size
      override fun createFragment(position: Int) = fragments[position]
    }
    TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
      when (position) {
        0 -> tab.text = "推荐"
        1 -> tab.text = "电影"
      }
    }.attach()
  }
}