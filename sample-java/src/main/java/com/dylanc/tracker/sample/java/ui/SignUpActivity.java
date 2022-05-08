package com.dylanc.tracker.sample.java.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dylanc.tracker.Tracker;
import com.dylanc.tracker.sample.java.databinding.ActivitySignUpBinding;
import com.dylanc.tracker.sample.java.track.ResultThreadNode;

/**
 * @author Dylan Cai
 */
public class SignUpActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivitySignUpBinding binding = ActivitySignUpBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    setTitle("注册");
    Tracker.setPageTrackNode(this);
    binding.btnSignUpSuccess.setOnClickListener(v -> {
      Tracker.updateTrackThreadNode(v, ResultThreadNode.class, (ResultThreadNode node) -> node.result = "success");
      Tracker.postTrack(v, "click_sign_up", ResultThreadNode.class);
    });
    binding.btnSignUpFailure.setOnClickListener(v -> {
      Tracker.updateTrackThreadNode(v, ResultThreadNode.class, (ResultThreadNode node) -> node.result = "failure");
      Tracker.postTrack(v, "click_sign_up", ResultThreadNode.class);
    });
  }
}