package com.dylanc.tracker

class TrackParams {
  private val map = mutableMapOf<String, String>()

  fun put(key: String, value: Any?): TrackParams = apply { map[key] = value.toString()}

  fun putAll(vararg pairs: Pair<String, String>): TrackParams = putAll(mapOf(*pairs))

  fun putAll(params: Map<String, String>): TrackParams = apply { map.putAll(params) }

  fun get(key: String): String? = map[key]

  fun toMap(): Map<String, String> = map

  override fun toString() = map.toString()
}