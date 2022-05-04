package com.dylanc.tracker.sample.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.tracker.*
import com.dylanc.tracker.sample.databinding.ActivityDetailsBinding
import com.dylanc.tracker.sample.track.RecordThreadNode

class DetailsActivity : AppCompatActivity() {

  private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    trackNode = PageTrackNode(mapOf("page_name" to "from_page")) {
      putAll("page_name" to "details")
    }
    getTrackThreadNode<RecordThreadNode>()?.isRecord = true
    postTrack("click_favorite", RecordThreadNode::class.java)
  }
}