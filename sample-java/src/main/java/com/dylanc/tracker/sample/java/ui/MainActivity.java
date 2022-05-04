package com.dylanc.tracker.sample.java.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dylanc.tracker.Tracker;
import com.dylanc.tracker.sample.java.R;
import com.dylanc.tracker.sample.java.track.RecordThreadNode;

import java.util.HashMap;

/**
 * @author Dylan Cai
 */
public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Tracker.setTrackNode(this, (params) ->
        params.put("key", "value")
            .put("key2", "value2")
    );
    Tracker.putTrackThreadNode(this, new RecordThreadNode());
    Tracker.postTrack(this, "test", RecordThreadNode.class);
  }
}
