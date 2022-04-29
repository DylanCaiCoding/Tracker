package com.dylanc.tracker

class TrackParams : Iterable<Any?> {
  private val map = mutableMapOf<String, Any?>()

  fun put(key: String, value: Any?): TrackParams = apply {
    map[key] = value
  }

  fun put(vararg pair: Pair<String, Any?>) {
    pair.forEach { map[it.first] = it.second }
  }

  fun putAll(params: Map<String, Any?>): TrackParams = apply {
    map.putAll(params)
  }

  fun get(key: String): Any? = map[key]

  fun toMap(): Map<String, Any?> = map

  override fun iterator() = map.iterator()

  override fun toString() = map.toString()
}