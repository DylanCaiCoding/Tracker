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

@file:Suppress("unused")

package com.dylanc.tracker.arouter

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.dylanc.tracker.putReferrerTrackNode

object ARouterWithTrack {

  @JvmStatic
  fun getInstance(): ARouterWithTrack = this

  fun build(path: String, activity: Activity): Postcard =
    ARouter.getInstance().build(path).withReferrerTrackNode(activity.window.decorView)

  fun build(path: String, fragment: Fragment): Postcard =
    ARouter.getInstance().build(path).withReferrerTrackNode(fragment.view)

  fun build(path: String, view: View?): Postcard =
    ARouter.getInstance().build(path).withReferrerTrackNode(view)

  private fun Postcard.withReferrerTrackNode(view: View?): Postcard = apply {
    extras?.putReferrerTrackNode(view)
  }
}