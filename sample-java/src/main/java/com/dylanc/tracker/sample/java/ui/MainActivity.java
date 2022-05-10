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

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dylanc.tracker.Tracker;
import com.dylanc.tracker.sample.java.R;
import com.dylanc.tracker.sample.java.databinding.ActivityMainBinding;
import com.dylanc.tracker.sample.java.ui.fragment.HomeFragment;
import com.dylanc.tracker.sample.java.ui.fragment.MineFragment;
import com.dylanc.tracker.sample.java.ui.fragment.TheaterFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan Cai
 */
public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    Tracker.setTrackNode(this, params -> params.put("page_name", "main"));

    List<Fragment> fragments = new ArrayList<>();
    fragments.add(new HomeFragment());
    fragments.add(new TheaterFragment());
    fragments.add(new MineFragment());
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
    binding.bottomNavigation.setOnItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.page_1:
          binding.viewPager2.setCurrentItem(0);
          break;
        case R.id.page_2:
          binding.viewPager2.setCurrentItem(1);
          break;
        case R.id.page_3:
          binding.viewPager2.setCurrentItem(2);
          break;
      }
      return true;
    });
  }
}