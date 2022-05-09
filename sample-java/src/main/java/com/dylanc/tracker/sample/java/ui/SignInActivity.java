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
      Tracker.setReferrerTrackNode(intent, v);
      startActivity(intent);
    });
  }
}