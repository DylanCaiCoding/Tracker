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

package com.dylanc.tracker

class TrackParams {
  private val map = mutableMapOf<String, String>()

  fun put(key: String, value: Any?): TrackParams = apply { map[key] = value.toString()}

  fun putAll(params: Map<String, String>): TrackParams = apply { map.putAll(params) }

  fun get(key: String): String? = map[key]

  fun toMap(): Map<String, String> = map

  internal fun internalRemove(key: String) = apply { map.remove(key) }

  override fun toString() = map.toString()
}