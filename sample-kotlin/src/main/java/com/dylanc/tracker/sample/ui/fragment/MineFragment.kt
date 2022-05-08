package com.dylanc.tracker.sample.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dylanc.tracker.postTrack
import com.dylanc.tracker.sample.R
import com.dylanc.tracker.sample.track.ResultThreadNode
import com.dylanc.tracker.sample.ui.SignInActivity

class MineFragment : Fragment(R.layout.fragment_mine) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.findViewById<View>(R.id.btn_sign_in).setOnClickListener {
      startActivity(Intent(requireContext(), SignInActivity::class.java))
    }
  }
}