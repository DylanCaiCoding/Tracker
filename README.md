# Tracker

English | [中文](README_ZH.md)

[![](https://www.jitpack.io/v/DylanCaiCoding/Tracker.svg)](https://www.jitpack.io/#DylanCaiCoding/Tracker) 
[![](https://img.shields.io/badge/License-Apache--2.0-blue.svg)](https://github.com/DylanCaiCoding/Tracker/blob/master/LICENSE)

Tracker is a lightweight tracking framework based on [the tracking idea of Buzzvideo](https://mp.weixin.qq.com/s/iMn--4FNugtH26G90N1MaQ).

## Tracking idea

[Why use chain of responsibility tracking?](https://dylancaicoding.github.io/Tracker/#/zh/idea)

## Sample

This library has Kotlin and Java sample code for simulating Buzzvideo tracking requirements.

## Get started

### Gradle

Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://www.jitpack.io' }
    }
}
```

Add dependencies：

```groovy
dependencies {
    implementation 'com.github.DylanCaiCoding:Tracker:1.0.0'
}
```

### Usage

> See the documentation for [Java usage](https://dylancaicoding.github.io/Tracker/#/zh/usage).

#### Initialization

```kotlin
initTracker(this, UMTrackHandler())
```

```kotlin
class UMTrackHandler : TrackHandler {
  override fun onEvent(context: Context, eventId: String, params: Map<String, String>) {
    MobclickAgent.onEvent(context, eventId, params) // Umeng sample
  }
}
```

#### Establish an in-page responsibility chain

Set the `trackNode` for the Activity/Fragment/View to add tracking parameters.

```kotlin
trackNode = TrackNode("channel_name" to "recommend")
```

```kotlin
holder.itemView.trackNode = TrackNode("video_id" to item.id, "video_type" to item.type)
```

#### Establish a page source responsibility chain

When starting an activity, you need to call `intent.putReferrerTrackNode(activity/fragment/view)` to set the source node.

```kotlin
val intent = Intent(activity, DetailsActivity::class.java).putReferrerTrackNode(view)
activity.startActivity(intent)
```

Then set a `PageTrackNode` in the the activity to establish a chain of source responsibilities between activity.

```kotlin
trackNode = PageTrackNode("page_name" to "details")
```

`PageTrackNode` will add the parameters of all the previous nodes to the page node, and some conversion rules can be set when adding. For example, the 'page_name' of the previous page, after jumping, report 'from_page'.

```kotlin
val referrerKeyMap = mapOf("page_name" to "from_page", "channel_name" to "from_channel_name")
trackNode = PageTrackNode(referrerKeyMap, "page_name" to "details")
```

#### Report tracking parameters

Calling `Activity/Fragment/View.postTrack(eventId)` collects parameters through the chain of responsibility and calls back to `TrackHandler`.

```kotlin
view.postTrack("click_favorite")
```

#### Thread node

In the activity, you can set up a thread node, which can share the tracking parameter between views or pages.

```kotlin
class RecordTrackNode : TrackNode {
  var isRecord = false

  override fun fillTackParams(params: TrackParams) {
    params.put("is_record", it)
  }
}

activity.putThreadTrackNode(RecordTrackNode())
```

You can then update the parameters of the thread node in Activity, Fragment, View.

```kotlin
view.updateThreadTrackNode<RecordTrackNode> { isRecord = true }
```

Declare the thread node class when reporting.

```kotlin
view.postTrack("click_publish", RecordTrackNode::class.java)
```

## Author's other libraries

| Library                                                      | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [Longan](https://github.com/DylanCaiCoding/Longan)           | A collection of Kotlin utils                                 |
| [LoadingStateView](https://github.com/DylanCaiCoding/LoadingStateView) | Decoupling the code of toolbar or loading status view. |
| [ViewBindingKTX](https://github.com/DylanCaiCoding/ViewBindingKTX) | The most comprehensive utils of ViewBinding.                 |
| [MMKV-KTX](https://github.com/DylanCaiCoding/MMKV-KTX)       | Use MMKV with property delegates.                                  |

## License

```
Copyright (C) 2022. Dylan Cai

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
