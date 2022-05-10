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

package com.dylanc.tracker.sample.java.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.dylanc.tracker.Tracker;
import com.dylanc.tracker.sample.java.bean.Video;
import com.dylanc.tracker.sample.java.databinding.ItemSeriesBinding;
import com.dylanc.tracker.sample.java.databinding.ItemVideoBinding;
import com.dylanc.tracker.sample.java.ui.DetailsActivity;

/**
 * @author Dylan Cai
 */
public class SeriesAdapter extends ListAdapter<Video, SeriesAdapter.ViewHolder> {

  private final Activity activity;

  public SeriesAdapter(Activity activity) {
    super(new Video.DiffCallback());
    this.activity = activity;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ViewHolder(activity, ItemSeriesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Video item = getItem(position);
    Tracker.setTrackNode(holder.itemView, params -> params.put("video_id", item.getId()).put("video_type", item.getType()));
    holder.binding.tvTitle.setText(item.getTitle());
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public ItemSeriesBinding binding;

    public ViewHolder(@NonNull Activity activity, @NonNull ItemSeriesBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      binding.btnFavorite.setOnClickListener(v -> Tracker.postTrack(v, "click_favorite"));
      itemView.setOnClickListener(v -> {
        Intent intent = new Intent(activity, DetailsActivity.class)
            .putExtra("video", getItem(getAdapterPosition()));
        Tracker.putReferrerTrackNode(intent, v);
        activity.startActivity(intent);
      });
    }
  }
}
