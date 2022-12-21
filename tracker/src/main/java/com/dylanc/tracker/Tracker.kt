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
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.io.Serializable

internal const val KEY_TRACK_PARAMS = "track_params"
internal const val KEY_TRACK_THREAD_NODES = "track_thread_nodes"
internal val allThreadNodes by lazy { mutableMapOf<String, TrackNode>() }
private lateinit var application: Application
private var trackHandler: TrackHandler? = null

@JvmName("init")
fun Application.initTracker(handler: TrackHandler) {
  application = this
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

fun Intent.putReferrerTrackNode(view: View?): Intent = apply {
  view?.let { putExtras(it.createTrackBundle()) }
}

fun View.createTrackBundle(): Bundle = Bundle().apply {
  putSerializable(KEY_TRACK_PARAMS, collectTrack() as? Serializable)
  putStringArray(KEY_TRACK_THREAD_NODES, findThreadNodeSet()?.map { it?.javaClass?.name }?.toTypedArray())
}

fun Activity.setPageTrackNode(trackNode: TrackNode) = setPageTrackNode(emptyMap(), trackNode)

@JvmOverloads
fun Activity.setPageTrackNode(referrerKeyMap: Map<String, String> = emptyMap(), trackNode: TrackNode = TrackNode { }) {
  this.trackNode = PageTrackNode(referrerKeyMap, trackNode)
}

fun Activity.postTrack(eventName: String, vararg classes: Class<*>) = window.decorView.postTrack(eventName, *classes)

fun Fragment.postTrack(eventName: String, vararg classes: Class<*>) = view?.postTrack(eventName, *classes)

fun View.postTrack(eventId: String, vararg classes: Class<*>) =
  trackHandler?.onEvent(application, eventId, collectTrack(*classes))

fun View.collectTrack(vararg classes: Class<*>): Map<String, String> {
  var view: View? = this
  val params = TrackParams()
  val nodeList = mutableListOf<TrackNode>()
  while (view != null) {
    view.trackNode?.let { nodeList.add(it) }
    view = view.parent as? View
  }
  nodeList.reversed().forEach { node -> node.fillTackParams(params) }
  findThreadNodeSet()?.filter { node -> classes.any { node?.javaClass?.name == it.name } }
    ?.forEach { node -> node?.fillTackParams(params) }
  return params.toMap()
}

fun ComponentActivity.putThreadTrackNode(trackNode: TrackNode) {
  val threadNodeSet = HashSet((window.decorView.getTag(R.id.tag_thread_nodes) as? Set<TrackNode?>).orEmpty())
  threadNodeSet.add(trackNode)
  window.decorView.setTag(R.id.tag_thread_nodes, threadNodeSet)
  allThreadNodes[trackNode.javaClass.name] = trackNode
  lifecycle.addObserver(object : DefaultLifecycleObserver {
    override fun onDestroy(owner: LifecycleOwner) {
      allThreadNodes.remove(trackNode.javaClass.name)
    }
  })
}

inline fun <reified T : TrackNode> Activity.updateThreadTrackNode(callback: Callback<T>) =
  updateThreadTrackNode(T::class.java, callback)

fun <T : TrackNode> Activity.updateThreadTrackNode(clazz: Class<T>, callback: Callback<T>) =
  window.decorView.updateThreadTrackNode(clazz, callback)

inline fun <reified T : TrackNode> Fragment.updateThreadTrackNode(callback: Callback<T>) =
  updateThreadTrackNode(T::class.java, callback)

fun <T : TrackNode> Fragment.updateThreadTrackNode(clazz: Class<T>, callback: Callback<T>) =
  view?.updateThreadTrackNode(clazz, callback)

inline fun <reified T : TrackNode> View.updateThreadTrackNode(callback: Callback<T>) =
  updateThreadTrackNode(T::class.java, callback)

fun <T : TrackNode> View.updateThreadTrackNode(clazz: Class<T>, callback: Callback<T>) =
  findThreadNodeSet()?.find { it?.javaClass?.name == clazz.name }?.let {
    callback.apply { (it as? T)?.onUpdate() }
  }

private fun View.findThreadNodeSet(): Set<TrackNode?>? =
  getTag(R.id.tag_thread_nodes) as? Set<TrackNode?> ?: (parent as? View)?.findThreadNodeSet()

fun interface Callback<T : TrackNode> {
  fun T.onUpdate()
}