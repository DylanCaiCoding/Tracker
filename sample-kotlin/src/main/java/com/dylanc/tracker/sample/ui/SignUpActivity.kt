package com.dylanc.tracker.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.tracker.*
import com.dylanc.tracker.sample.databinding.ActivitySignUpBinding
import com.dylanc.tracker.sample.track.ResultThrackNode

class SignUpActivity : AppCompatActivity() {
  private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    title = "注册"
    trackNode = PageTrackNode()
    binding.btnSignUpSuccess.setOnClickListener { view ->
      view.updateThreadTrackNode<ResultThrackNode> { result = "success" }
      view.postTrack("click_sign_up", ResultThrackNode::class.java)
    }
    binding.btnSignUpFailure.setOnClickListener { view ->
      view.updateThreadTrackNode<ResultThrackNode> { result = "failure" }
      view.postTrack("click_sign_up", ResultThrackNode::class.java)
    }
  }
}