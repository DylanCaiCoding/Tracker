/*
 * Copyright (c) 2022. Dylan Cai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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