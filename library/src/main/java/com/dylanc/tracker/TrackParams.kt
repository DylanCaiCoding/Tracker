package com.dylanc.tracker

class TrackParams {
  private val map = mutableMapOf<String, Any?>()

  fun put(key: String, value: Any?): TrackParams = apply { map[key] = value }

  fun putAll(vararg pairs: Pair<String, Any?>): TrackParams = putAll(mapOf(*pairs))

  fun putAll(params: Map<String, Any?>): TrackParams = apply { map.putAll(params) }

  fun get(key: String): Any? = map[key]

  fun toMap(): Map<String, Any?> = map

  override fun toString() = map.toString()
}