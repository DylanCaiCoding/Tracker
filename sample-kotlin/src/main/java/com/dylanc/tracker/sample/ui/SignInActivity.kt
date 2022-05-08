package com.dylanc.tracker.sample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.tracker.postTrack
import com.dylanc.tracker.putTrackThreadNode
import com.dylanc.tracker.sample.databinding.ActivitySignInBinding
import com.dylanc.tracker.sample.track.ResultThreadNode
import com.dylanc.tracker.setReferrerTrackNode
import com.dylanc.tracker.updateTrackThreadNode

class SignInActivity : AppCompatActivity() {
  private val binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    title = "登录"
    putTrackThreadNode(ResultThreadNode())
    binding.btnSignInSuccess.setOnClickListener { view ->
      view.updateTrackThreadNode<ResultThreadNode> { result = "success" }
      view.postTrack("click_sign_in", ResultThreadNode::class.java)
    }
    binding.btnSignInFailure.setOnClickListener { view ->
      view.updateTrackThreadNode<ResultThreadNode> { result = "failure" }
      view.postTrack("click_sign_in", ResultThreadNode::class.java)
    }
    binding.btnSignUp.setOnClickListener { view ->
      startActivity(Intent(this, SignUpActivity::class.java).setReferrerTrackNode(view))
    }
  }
}