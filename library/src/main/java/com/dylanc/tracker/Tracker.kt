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

@file:JvmName("Tracker")
@file:Suppress("unused", "UNCHECKED_CAST")

package com.dylanc.tracker

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.io.Serializable

internal const val KEY_TRACK_PARAMS = "track_params"
internal const val KEY_TRACK_THREAD_NODES = "track_thread_nodes"
private lateinit var application: Application
private var trackHandler: TrackHandler? = null
private val threadNodeCache by lazy { mutableMapOf<Class<*>, TrackNode>() }

@JvmName("init")
fun initTracker(application: Application, handler: TrackHandler) {
  com.dylanc.tracker.application = application
  trackHandler = handler
}

var Activity.trackNode: TrackNode?
  get() = window.decorView.trackNode
  set(value) {
    window.decorView.trackNode = value
  }

var Fragment.trackNode: TrackNode?
  get() = view?.trackNode
  set(value) {
    view?.trackNode = value
  }

var View.trackNode: TrackNode?
  get() = getTag(R.id.tag_track_node) as? TrackNode
  set(value) {
    setTag(R.id.tag_track_node, value)
  }

fun Intent.putReferrerTrackNode(activity: Activity): Intent = putReferrerTrackNode(activity.window.decorView)

fun Intent.putReferrerTrackNode(fragment: Fragment): Intent = putReferrerTrackNode(fragment.view)

fun Intent.putReferrerTrackNode(view: View?): Intent = putExtra(KEY_TRACK_PARAMS, view?.collectTrack() as? Serializable)
  .putExtra(KEY_TRACK_THREAD_NODES, view?.threadNodeClasses?.let { list -> Array(list.size) { list[it].name } })

fun ComponentActivity.setPageTrackNode(trackNode: TrackNode) = setPageTrackNode(emptyMap(), trackNode)

@JvmOverloads
fun ComponentActivity.setPageTrackNode(referrerKeyMap: Map<String, String> = emptyMap(), trackNode: TrackNode = TrackNode { }) {
  this.trackNode = PageTrackNode(referrerKeyMap, trackNode)
}

fun Activity.postTrack(eventName: String, vararg clazz: Class<*>) = window.decorView.postTrack(eventName, *clazz)

fun Fragment.postTrack(eventName: String, vararg clazz: Class<*>) = view?.postTrack(eventName, *clazz)

fun View.postTrack(eventId: String, vararg clazz: Class<*>) {
  trackHandler?.onEvent(application, eventId, collectTrack(*clazz))
}

fun View.collectTrack(vararg classes: Class<*>): Map<String, String> {
  var view: View? = this
  val params = TrackParams()
  val nodeList = mutableListOf<TrackNode>()
  while (view != null) {
    view.trackNode?.let { nodeList.add(it) }
    view = view.parent as? View
  }
  nodeList.reversed().forEach { node -> node.fillTackParams(params) }
  classes.forEach { clazz ->
    if (threadNodeClasses?.any { it == clazz } == true) {
      threadNodeCache[clazz]?.fillTackParams(params)
    }
  }
  return params.toMap()
}

fun ComponentActivity.putThreadTrackNode(trackNode: TrackNode) {
  if (threadNodeClasses == null) {
    threadNodeClasses = mutableListOf(trackNode.javaClass)
  } else {
    threadNodeClasses!!.add(trackNode.javaClass)
  }
  threadNodeCache[trackNode.javaClass] = trackNode
  lifecycle.addObserver(object : DefaultLifecycleObserver {
    override fun onResume(owner: LifecycleOwner) {
      if (threadNodeCache[trackNode.javaClass] == null) {
        threadNodeCache[trackNode.javaClass] = trackNode
      }
    }

    override fun onDestroy(owner: LifecycleOwner) {
      threadNodeCache.remove(trackNode.javaClass)
    }
  })
}

inline fun <reified T : TrackNode> Activity.updateThreadTrackNode(callback: Callback<T>) = updateThreadTrackNode(T::class.java, callback)

fun <T : TrackNode> Activity.updateThreadTrackNode(clazz: Class<T>, callback: Callback<T>) = window.decorView.updateThreadTrackNode(clazz, callback)

inline fun <reified T : TrackNode> Fragment.updateThreadTrackNode(callback: Callback<T>) = updateThreadTrackNode(T::class.java, callback)

fun <T : TrackNode> Fragment.updateThreadTrackNode(clazz: Class<T>, callback: Callback<T>) = view?.updateThreadTrackNode(clazz, callback)

inline fun <reified T : TrackNode> View.updateThreadTrackNode(callback: Callback<T>) = updateThreadTrackNode(T::class.java, callback)

fun <T : TrackNode> View.updateThreadTrackNode(clazz: Class<T>, callback: Callback<T>) =
  (if (threadNodeClasses?.contains(clazz) == true) threadNodeCache[clazz] as? T else null)?.let { callback.apply { it.onUpdate() } }

internal var Activity.threadNodeClasses: MutableList<Class<*>>?
  get() = window.decorView.getTag(R.id.tag_track_thread) as? MutableList<Class<*>>
  set(value) {
    window.decorView.setTag(R.id.tag_track_thread, value)
  }

private val View.threadNodeClasses: List<Class<*>>?
  get() {
    var view: View? = this
    while (view != null) {
      if (view.getTag(R.id.tag_track_thread) != null) {
        return view.getTag(R.id.tag_track_thread) as List<Class<*>>
      }
      view = view.parent as? View
    }
    return null
  }

fun interface Callback<T : TrackNode> {
  fun T.onUpdate()
}