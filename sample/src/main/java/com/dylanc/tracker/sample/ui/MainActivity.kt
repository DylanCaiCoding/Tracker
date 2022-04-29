package com.dylanc.tracker.sample.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dylanc.tracker.*
import com.dylanc.tracker.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    fillTrack { put("current_page" to "main") }
    binding.card.fillTrack { put("group_id" to 1) }
    binding.btnTest.fillTrack { put("device_id" to 2) }
    binding.btnTest.setOnClickListener {
      startActivity(Intent(this, DetailsActivity::class.java).setTrack(it))
    }
  }
}