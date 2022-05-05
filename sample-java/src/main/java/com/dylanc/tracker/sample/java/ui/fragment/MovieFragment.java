package com.dylanc.tracker.sample.java.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dylanc.tracker.Tracker;
import com.dylanc.tracker.sample.java.R;
import com.dylanc.tracker.sample.java.adapter.VideoAdapter;
import com.dylanc.tracker.sample.java.repository.DataRepository;

/**
 * @author Dylan Cai
 */
public class MovieFragment extends Fragment {

  public MovieFragment() {
    super(R.layout.layout_list);
  }


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Tracker.setTrackNode(this, params -> params.put("channel_name", "movie"));

    RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
    VideoAdapter adapter = new VideoAdapter(requireActivity());
    adapter.submitList(DataRepository.getMovieVideos());
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
  }
}
