package com.dylanc.tracker.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dylanc.tracker.TrackNode
import com.dylanc.tracker.sample.R
import com.dylanc.tracker.sample.const.UM_CHANNEL
import com.dylanc.tracker.sample.const.UM_APP_ID
import com.dylanc.tracker.sample.databinding.ActivityMainBinding
import com.dylanc.tracker.sample.ui.fragment.HomeFragment
import com.dylanc.tracker.sample.ui.fragment.MineFragment
import com.dylanc.tracker.sample.ui.fragment.TheaterFragment
import com.dylanc.tracker.trackNode
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

class MainActivity : AppCompatActivity() {

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    UMConfigure.init(this, UM_APP_ID, UM_CHANNEL, UMConfigure.DEVICE_TYPE_PHONE, "")
    trackNode = TrackNode("page_name" to "main")

    val fragments = listOf(HomeFragment(), TheaterFragment(), MineFragment())
    binding.viewPager2.adapter = object : FragmentStateAdapter(this) {
      override fun getItemCount() = fragments.size
      override fun createFragment(position: Int) = fragments[position]
    }
    binding.bottomNavigation.setOnItemSelectedListener {
      when (it.itemId) {
        R.id.page_1 -> binding.viewPager2.currentItem = 0
        R.id.page_2 -> binding.viewPager2.currentItem = 1
        R.id.page_3 -> binding.viewPager2.currentItem = 2
      }
      true
    }
  }

  override fun onBackPressed() {
    MobclickAgent.onKillProcess(this)
    super.onBackPressed()
  }
}