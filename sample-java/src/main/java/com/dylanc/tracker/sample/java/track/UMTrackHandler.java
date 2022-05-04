package com.dylanc.tracker.sample.java.track;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dylanc.tracker.BuildConfig;
import com.dylanc.tracker.TrackHandler;

import java.util.Map;

/**
 * @author Dylan Cai
 */
public class UMTrackHandler implements TrackHandler {

  @Override
  public void onEvent(@NonNull Context context, @NonNull String eventId, @NonNull Map<String, String> params) {
    if (BuildConfig.DEBUG) {
      Log.d("Tracker", "onEvent: eventId = " + eventId + ", params = " + params);
    }
  }
}