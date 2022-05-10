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

package com.dylanc.tracker.sample.java.bean;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.io.Serializable;

/**
 * @author Dylan Cai
 */
public class Video implements Serializable {
  private String id;
  private String title;
  private String type;
  private String seriesName;

  public Video(String id, String title, String type) {
    this.id = id;
    this.title = title;
    this.type = type;
  }

  public Video(String id, String title, String type, String seriesName) {
    this.id = id;
    this.title = title;
    this.type = type;
    this.seriesName = seriesName;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }

  public String getSeriesName() {
    return seriesName;
  }

  public static class DiffCallback extends DiffUtil.ItemCallback<Video> {

    @Override
    public boolean areItemsTheSame(@NonNull Video oldItem, @NonNull Video newItem) {
      return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Video oldItem, @NonNull Video newItem) {
      return oldItem.getTitle().equals(newItem.getTitle());
    }
  }
}
