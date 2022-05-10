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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dylanc.tracker.Tracker;
import com.dylanc.tracker.sample.java.adapter.SeriesAdapter;
import com.dylanc.tracker.sample.java.bean.Video;
import com.dylanc.tracker.sample.java.databinding.LayoutListBinding;
import com.dylanc.tracker.sample.java.repository.DataRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dylan Cai
 */
public class SeriesActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LayoutListBinding binding = LayoutListBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    Tracker.setPageTrackNode(this, getReferrerKeyMap(), params -> params.put("page_name", "series"));

    Video video = (Video) getIntent().getSerializableExtra("video");
    setTitle(video.getSeriesName());
    SeriesAdapter adapter = new SeriesAdapter(this);
    adapter.submitList(DataRepository.getSeriesMovies(video.getId()));
    binding.recyclerView.setAdapter(adapter);
  }

  private Map<String, String> getReferrerKeyMap() {
    HashMap<String, String> map = new HashMap<>();
    map.put("page_name", "from_page");
    map.put("tab_name", "from_tab_name");
    map.put("channel_name", "from_channel_name");
    return map;
  }
}