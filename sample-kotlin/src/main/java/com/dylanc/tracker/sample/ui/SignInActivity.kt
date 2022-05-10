/*
 * Copyright (c) 2022. Dylan Cai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dylanc.tracker.sample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.tracker.postTrack
import com.dylanc.tracker.putThreadTrackNode
import com.dylanc.tracker.sample.databinding.ActivitySignInBinding
import com.dylanc.tracker.sample.track.ResultTrackNode
import com.dylanc.tracker.putReferrerTrackNode
import com.dylanc.tracker.updateThreadTrackNode

class SignInActivity : AppCompatActivity() {
  private val binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    title = "登录"
    putThreadTrackNode(ResultTrackNode())
    binding.btnSignInSuccess.setOnClickListener { view ->
      view.updateThreadTrackNode<ResultTrackNode> { result = "success" }
      view.postTrack("click_sign_in", ResultTrackNode::class.java)
    }
    binding.btnSignInFailure.setOnClickListener { view ->
      view.updateThreadTrackNode<ResultTrackNode> { result = "failure" }
      view.postTrack("click_sign_in", ResultTrackNode::class.java)
    }
    binding.btnSignUp.setOnClickListener { view ->
      startActivity(Intent(this, SignUpActivity::class.java).putReferrerTrackNode(view))
    }
  }
}