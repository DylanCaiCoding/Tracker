package com.dylanc.tracker.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.tracker.TrackNode
import com.dylanc.tracker.sample.databinding.ActivityMainBinding
import com.dylanc.tracker.trackNode

class MainActivity : AppCompatActivity() {

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    trackNode = TrackNode {
      put("page_name" to "main")
    }
    binding.card.trackNode = TrackNode {
      put("group_id" to 1)
    }
    binding.btnTest.trackNode = TrackNode {
      put("device_id" to 2)
    }
    binding.btnTest.setOnClickListener {
      DetailsActivity.start(this, it)
    }
  }
}