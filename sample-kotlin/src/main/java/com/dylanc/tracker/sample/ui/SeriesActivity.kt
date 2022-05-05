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
    trackNode = PageTrackNode(referrerKeyMap) {
      put("page_name", "series")
    }

    val video = intent.getParcelableExtra<Video>("video")!!
    title = video.seriesName
    val adapter = SeriesAdapter(this)
    adapter.submitList(DataRepository.getSeriesMovies(video.id))
    binding.recyclerView.adapter = adapter
  }
}