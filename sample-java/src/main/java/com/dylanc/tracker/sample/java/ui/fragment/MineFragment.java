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
