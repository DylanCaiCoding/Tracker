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

package com.dylanc.tracker.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.tracker.PageTrackNode
import com.dylanc.tracker.sample.adapter.SeriesAdapter
import com.dylanc.tracker.sample.bean.Video
import com.dylanc.tracker.sample.const.referrerKeyMap
import com.dylanc.tracker.sample.databinding.LayoutListBinding
import com.dylanc.tracker.sample.repository.DataRepository
import com.dylanc.tracker.trackNode

class SeriesActivity : AppCompatActivity() {
  private val binding by lazy { LayoutListBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    trackNode = PageTrackNode(referrerKeyMap, "page_name" to "series")

    val video = intent.getParcelableExtra<Video>("video")!!
    title = video.seriesName
    val adapter = SeriesAdapter(this)
    adapter.submitList(DataRepository.getSeriesMovies(video.id))
    binding.recyclerView.adapter = adapter
  }
}