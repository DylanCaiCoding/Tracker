package com.dylanc.tracker.sample.java;

import android.app.Application;

import com.dylanc.tracker.Tracker;

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
