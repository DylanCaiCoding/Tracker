package com.dylanc.tracker.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.tracker.TrackNode
import com.dylanc.tracker.sample.databinding.ActivityMainBinding
import com.dylanc.tracker.trackNode
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

class MainActivity : AppCompatActivity() {

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    UMConfigure.init(this, "626ca3f630a4f67780c223e6", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "")
    trackNode = TrackNode {
      put("page_name", "main")
    }
    binding.card.trackNode = TrackNode {
      put("group_id", 1)
    }
    binding.btnTest.trackNode = TrackNode {
      put("device_id", 2)
    }
    binding.btnTest.setOnClickListener {
      DetailsActivity.start(this, it)
    }
  }

  override fun onBackPressed() {
    MobclickAgent.onKillProcess(this)
    super.onBackPressed()
  }
}