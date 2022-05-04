package com.dylanc.tracker.sample.java;

import android.app.Application;

import com.dylanc.tracker.Tracker;
import com.dylanc.tracker.sample.java.track.UMTrackHandler;

/**
 * @author Dylan Cai
 */
public class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Tracker.init(this, new UMTrackHandler());
  }
}
