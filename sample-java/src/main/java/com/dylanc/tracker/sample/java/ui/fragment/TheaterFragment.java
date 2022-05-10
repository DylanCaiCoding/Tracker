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

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dylanc.tracker.TrackNode;
import com.dylanc.tracker.TrackParams;
import com.dylanc.tracker.Tracker;
import com.dylanc.tracker.sample.java.R;
import com.dylanc.tracker.sample.java.databinding.FragmentTheaterBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan Cai
 */
public class TheaterFragment extends Fragment {

  public TheaterFragment() {
    super(R.layout.fragment_theater);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Tracker.setTrackNode(this, params -> params.put("tab_name", "theater"));

    FragmentTheaterBinding binding = FragmentTheaterBinding.bind(view);
    List<Fragment> fragments = new ArrayList<>();
    fragments.add(new RecommendFragment());
    fragments.add(new MovieFragment());
    binding.viewPager2.setAdapter(new FragmentStateAdapter(this) {
      @NonNull
      @Override
      public Fragment createFragment(int position) {
        return fragments.get(position);
      }

      @Override
      public int getItemCount() {
        return fragments.size();
      }
    });
    new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) -> {
      switch (position) {
        case 0:
          tab.setText("推荐");
          break;
        case 1:
          tab.setText("电影");
          break;
      }
    }).attach();
  }
}
