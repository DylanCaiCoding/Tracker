package com.dylanc.tracker.sample.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dylanc.tracker.TrackNode
import com.dylanc.tracker.sample.R
import com.dylanc.tracker.sample.adapter.VideoAdapter
import com.dylanc.tracker.sample.repository.DataRepository
import com.dylanc.tracker.trackNode

class MovieFragment : Fragment(R.layout.layout_list) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    trackNode = TrackNode("channel_name" to "movie")

    val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
    val adapter = VideoAdapter(requireActivity())
    adapter.submitList(DataRepository.movieVideoList)
    recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    recyclerView.adapter = adapter
  }
}