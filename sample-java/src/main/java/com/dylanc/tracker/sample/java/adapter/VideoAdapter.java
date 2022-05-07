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
import com.dylanc.tracker.sample.java.databinding.ItemVideoBinding;
import com.dylanc.tracker.sample.java.ui.DetailsActivity;
import com.dylanc.tracker.sample.java.ui.SeriesActivity;

/**
 * @author Dylan Cai
 */
public class VideoAdapter extends ListAdapter<Video, VideoAdapter.ViewHolder> {

  private final Activity activity;

  public VideoAdapter(Activity activity) {
    super(new Video.DiffCallback());
    this.activity = activity;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ViewHolder(activity, ItemVideoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Video item = getItem(position);
    Tracker.setTrackNode(holder.itemView, params -> params.put("video_id", item.getId()));

    ItemVideoBinding binding = holder.binding;
    binding.tvTitle.setText(item.getTitle());
    if (item.getSeriesName() == null) {
      binding.tvSeriesName.setVisibility(ViewGroup.GONE);
    } else {
      binding.tvSeriesName.setVisibility(ViewGroup.VISIBLE);
      binding.tvSeriesName.setText(item.getSeriesName());
    }
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public ItemVideoBinding binding;

    public ViewHolder(@NonNull Activity activity, @NonNull ItemVideoBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      binding.btnFavorite.setOnClickListener(v -> Tracker.postTrack(v, "click_favorite"));
      binding.tvSeriesName.setOnClickListener(v -> {
        Intent intent = new Intent(activity, SeriesActivity.class)
            .putExtra("video", getItem(getAdapterPosition()));
        Tracker.setReferrerTrackNode(intent, v);
        activity.startActivity(intent);
      });
      itemView.setOnClickListener(v -> {
        Intent intent = new Intent(activity, DetailsActivity.class)
            .putExtra("video", getItem(getAdapterPosition()));
        Tracker.setReferrerTrackNode(intent, v);
        activity.startActivity(intent);
      });
    }
  }
}
