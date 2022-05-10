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

package com.dylanc.tracker.sample.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dylanc.tracker.TrackNode
import com.dylanc.tracker.postTrack
import com.dylanc.tracker.putReferrerTrackNode
import com.dylanc.tracker.sample.bean.Video
import com.dylanc.tracker.sample.databinding.ItemVideoBinding
import com.dylanc.tracker.sample.ui.DetailsActivity
import com.dylanc.tracker.sample.ui.SeriesActivity
import com.dylanc.tracker.trackNode

class VideoAdapter(private val activity: Activity) : ListAdapter<Video, VideoAdapter.ViewHolder>(Video.DiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    ViewHolder(ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
      .apply {
        binding.btnFavorite.setOnClickListener { view ->
          view.postTrack("click_favorite")
        }
        binding.tvSeriesName.setOnClickListener { view ->
          val intent = Intent(activity, SeriesActivity::class.java)
            .putExtra("video", getItem(adapterPosition))
            .putReferrerTrackNode(view)
          activity.startActivity(intent)
        }
        itemView.setOnClickListener { view ->
          val intent = Intent(activity, DetailsActivity::class.java)
            .putExtra("video", getItem(adapterPosition))
            .putReferrerTrackNode(view)
          activity.startActivity(intent)
        }
      }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = getItem(position)
    holder.itemView.trackNode = TrackNode("video_id" to item.id, "video_type" to item.type)

    holder.binding.apply {
      tvTitle.text = item.title
      tvSeriesName.text = item.seriesName
      tvSeriesName.isVisible = item.seriesName != null
    }
  }

  class ViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root)
}