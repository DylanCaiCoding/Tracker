package com.dylanc.tracker.sample.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.tracker.PageTrackNode
import com.dylanc.tracker.postTrack
import com.dylanc.tracker.putTrackNode
import com.dylanc.tracker.sample.databinding.ActivityDetailsBinding
import com.dylanc.tracker.trackNode

class DetailsActivity : AppCompatActivity() {

  private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    trackNode = PageTrackNode(mapOf("page_name" to "from_page")) {
      put("page_name" to "details")
    }
    postTrack("click_favorite")
  }

  companion object {
    fun start(context: AppCompatActivity, view: View) {
      val intent = Intent(context, DetailsActivity::class.java).putTrackNode(view)
      context.startActivity(intent)
    }
  }
}