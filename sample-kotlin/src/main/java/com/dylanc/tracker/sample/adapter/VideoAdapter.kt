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
import com.dylanc.tracker.setReferrerTrackNode
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
            .setReferrerTrackNode(view)
          activity.startActivity(intent)
        }
        itemView.setOnClickListener { view ->
          val intent = Intent(activity, DetailsActivity::class.java)
            .putExtra("video", getItem(adapterPosition))
            .setReferrerTrackNode(view)
          activity.startActivity(intent)
        }
      }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = getItem(position)
    holder.itemView.trackNode = TrackNode("video_id" to item.id)

    holder.binding.apply {
      tvTitle.text = item.title
      tvSeriesName.text = item.seriesName
      tvSeriesName.isVisible = item.seriesName != null
    }
  }

  class ViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root)
}