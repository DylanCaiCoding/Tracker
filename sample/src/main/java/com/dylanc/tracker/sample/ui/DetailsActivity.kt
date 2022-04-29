package com.dylanc.tracker.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.tracker.fillTrack
import com.dylanc.tracker.postTrack
import com.dylanc.tracker.sample.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

  private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    fillTrack()
    postTrack("click_favorite")
  }
}