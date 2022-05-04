package com.dylanc.tracker.sample.java.track;

import androidx.annotation.NonNull;

import com.dylanc.tracker.TrackNode;
import com.dylanc.tracker.TrackParams;

/**
 * @author Dylan Cai
 */
public class RecordThreadNode implements TrackNode {

  public boolean isRecord = false;

  @Override
  public void fillTackParams(@NonNull TrackParams params) {
    params.put("is_record", isRecord);
  }
}
