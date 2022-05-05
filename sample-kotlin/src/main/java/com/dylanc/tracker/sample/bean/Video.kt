package com.dylanc.tracker.sample.bean

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
  val id: String,
  val title: String,
  val seriesName: String? = null,
) : Parcelable {

  class DiffCallback : DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Video, newItem: Video) = oldItem == newItem
  }
}
