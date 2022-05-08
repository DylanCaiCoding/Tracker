package com.dylanc.tracker.sample.java.track;

import androidx.annotation.NonNull;

import com.dylanc.tracker.TrackNode;
import com.dylanc.tracker.TrackParams;

/**
 * @author Dylan Cai
 */
public class ResultThreadNode implements TrackNode {

  public String result = null;

  @Override
  public void fillTackParams(@NonNull TrackParams params) {
    if (result != null) {
      params.put("result", result);
    }
  }
}
