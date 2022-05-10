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

package com.dylanc.tracker.sample.java.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dylanc.tracker.Tracker;
import com.dylanc.tracker.sample.java.databinding.ActivitySignInBinding;
import com.dylanc.tracker.sample.java.track.ResultTrackNode;

/**
 * @author Dylan Cai
 */
public class SignInActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivitySignInBinding binding = ActivitySignInBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    setTitle("登录");
    Tracker.putThreadTrackNode(this, new ResultTrackNode());
    binding.btnSignInSuccess.setOnClickListener(v -> {
      Tracker.updateThreadTrackNode(v, ResultTrackNode.class, node -> node.result = "success");
      Tracker.postTrack(v, "click_sign_in", ResultTrackNode.class);
    });
    binding.btnSignInFailure.setOnClickListener(v -> {
      Tracker.updateThreadTrackNode(v, ResultTrackNode.class, node -> node.result = "failure");
      Tracker.postTrack(v, "click_sign_in", ResultTrackNode.class);
    });
    binding.btnSignUp.setOnClickListener(v -> {
      Intent intent = new Intent(this, SignUpActivity.class);
      Tracker.putReferrerTrackNode(intent, v);
      startActivity(intent);
    });
  }
}