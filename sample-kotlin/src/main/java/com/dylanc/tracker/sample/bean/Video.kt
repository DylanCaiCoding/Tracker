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

package com.dylanc.tracker.sample.bean

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
  val id: String,
  val title: String,
  val type: String,
  val seriesName: String? = null,
) : Parcelable {

  class DiffCallback : DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Video, newItem: Video) = oldItem == newItem
  }
}
