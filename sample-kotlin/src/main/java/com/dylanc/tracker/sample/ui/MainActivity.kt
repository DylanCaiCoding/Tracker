package com.dylanc.tracker.sample.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.tracker.*
import com.dylanc.tracker.sample.databinding.ActivityMainBinding
import com.dylanc.tracker.sample.track.RecordThreadNode
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
    putTrackThreadNode(RecordThreadNode())
    binding.card.trackNode = TrackNode {
      put("group_id", 1)
    }
    binding.btnTest.trackNode = TrackNode {
      put("device_id", 2)
    }
    binding.btnTest.setOnClickListener {
      startActivity(Intent(this, DetailsActivity::class.java).putTrack(it))
    }
  }

  override fun onBackPressed() {
    MobclickAgent.onKillProcess(this)
    super.onBackPressed()
  }
}