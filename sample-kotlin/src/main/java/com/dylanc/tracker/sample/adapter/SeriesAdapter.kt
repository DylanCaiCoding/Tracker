package com.dylanc.tracker.sample.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dylanc.tracker.TrackNode
import com.dylanc.tracker.postTrack
import com.dylanc.tracker.putTrack
import com.dylanc.tracker.sample.bean.Video
import com.dylanc.tracker.sample.databinding.ItemSeriesBinding
import com.dylanc.tracker.sample.databinding.ItemVideoBinding
import com.dylanc.tracker.sample.ui.DetailsActivity
import com.dylanc.tracker.sample.ui.SeriesActivity
import com.dylanc.tracker.trackNode

class SeriesAdapter(
  private val activity: Activity
) : ListAdapter<Video, SeriesAdapter.ViewHolder>(Video.DiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    ViewHolder(ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
      .apply {
        itemView.setOnClickListener {
          val intent = Intent(activity, DetailsActivity::class.java)
            .putExtra("video", getItem(adapterPosition))
            .putTrack(it)
          activity.startActivity(intent)
        }
        binding.btnFavorite.setOnClickListener {
          it.postTrack("click_favorite")
        }
      }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.binding.tvTitle.text = getItem(position).title
  }

  class ViewHolder(val binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root)
}