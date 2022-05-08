package com.dylanc.tracker.sample.java.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dylanc.tracker.sample.java.R;
import com.dylanc.tracker.sample.java.ui.SignInActivity;

/**
 * @author Dylan Cai
 */
public class MineFragment extends Fragment {

  public MineFragment() {
    super(R.layout.fragment_mine);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.findViewById(R.id.btn_sign_in).setOnClickListener((v) ->
        startActivity(new Intent(requireContext(), SignInActivity.class)));
  }
}
