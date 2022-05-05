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
  private String seriesName;

  public Video(String id, String title) {
    this(id, title, null);
  }

  public Video(String id, String title, String seriesName) {
    this.id = id;
    this.title = title;
    this.seriesName = seriesName;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setSeriesName(String seriesName) {
    this.seriesName = seriesName;
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
