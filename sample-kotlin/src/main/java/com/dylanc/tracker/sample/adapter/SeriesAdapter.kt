package com.dylanc.tracker.sample.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dylanc.tracker.TrackNode
import com.dylanc.tracker.postTrack
import com.dylanc.tracker.setReferrerTrackNode
import com.dylanc.tracker.sample.bean.Video
import com.dylanc.tracker.sample.databinding.ItemSeriesBinding
import com.dylanc.tracker.sample.ui.DetailsActivity
import com.dylanc.tracker.trackNode

class SeriesAdapter(private val activity: Activity) : ListAdapter<Video, SeriesAdapter.ViewHolder>(Video.DiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    ViewHolder(ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
      .apply {
        binding.btnFavorite.setOnClickListener { view ->
          view.postTrack("click_favorite")
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
    holder.binding.tvTitle.text = item.title
  }

  class ViewHolder(val binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root)
}