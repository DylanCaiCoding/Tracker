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

package com.dylanc.tracker.sample.const

const val UM_APP_ID = "626ca3f630a4f67780c223e6"
const val UM_CHANNEL = "Umeng"

val referrerKeyMap by lazy {
  mapOf("page_name" to "from_page", "tab_name" to "from_tab_name", "channel_name" to "from_channel_name")
}