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

fun Activity.postTrack(eventName: String, vararg clazz: Class<*>) = window.decorView.postTrack(eventName, *clazz)

fun Fragment.postTrack(eventName: String, vararg clazz: Class<*>) = view?.postTrack(eventName, *clazz)

fun View.postTrack(eventId: String, vararg clazz: Class<*>) {
  trackHandler?.onEvent(application, eventId, collectTrack(*clazz))
}

fun Intent.setReferrerTrackNode(activity: Activity): Intent = setReferrerTrackNode(activity.window.decorView)

fun Intent.setReferrerTrackNode(fragment: Fragment): Intent = setReferrerTrackNode(fragment.view)

fun Intent.setReferrerTrackNode(view: View?): Intent = putExtra(KEY_TRACK_PARAMS, view?.collectTrack() as? Serializable)
  .putExtra(KEY_TRACK_THREAD_NODES, view?.threadNodeClasses?.let { list -> Array(list.size) { list[it].name } })

fun ComponentActivity.setPageTrackNode(trackNode: TrackNode) = setPageTrackNode(emptyMap(), trackNode)

@JvmOverloads
fun ComponentActivity.setPageTrackNode(referrerKeyMap: Map<String, String> = emptyMap(), trackNode: TrackNode = TrackNode { }) {
  this.trackNode = PageTrackNode(referrerKeyMap, trackNode)
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

fun ComponentActivity.putTrackThreadNode(trackNode: TrackNode) {
  if (threadNodeClasses == null) {
    threadNodeClasses = mutableListOf(trackNode.javaClass)
  } else {
    threadNodeClasses!!.add(trackNode.javaClass)
  }
  threadNodeCache[trackNode.javaClass] = trackNode
  lifecycle.addObserver(object : DefaultLifecycleObserver {
    override fun onDestroy(owner: LifecycleOwner) {
      threadNodeCache.remove(trackNode.javaClass)
    }
  })
}

inline fun <reified T : TrackNode> Activity.getTrackThreadNode(): T? = getTrackThreadNode(T::class.java)

fun <T : TrackNode> Activity.getTrackThreadNode(clazz: Class<T>): T? = window.decorView.getTrackThreadNode(clazz)

inline fun <reified T : TrackNode> Fragment.getTrackThreadNode(): T? = getTrackThreadNode(T::class.java)

fun <T : TrackNode> Fragment.getTrackThreadNode(clazz: Class<T>): T? = view?.getTrackThreadNode(clazz)

inline fun <reified T : TrackNode> View.getTrackThreadNode(): T? = getTrackThreadNode(T::class.java)

fun <T : TrackNode> View.getTrackThreadNode(clazz: Class<T>): T? =
  if (threadNodeClasses?.contains(clazz) == true) threadNodeCache[clazz] as? T else null

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