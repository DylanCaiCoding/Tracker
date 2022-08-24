# Tracker

English | [中文](README_ZH.md)

[![](https://www.jitpack.io/v/DylanCaiCoding/Tracker.svg)](https://www.jitpack.io/#DylanCaiCoding/Tracker)
[![](https://img.shields.io/badge/License-Apache--2.0-blue.svg)](https://github.com/DylanCaiCoding/Tracker/blob/master/LICENSE)

Tracker is a lightweight tracking framework based on [the tracking idea of Buzzvideo](https://mp.weixin.qq.com/s/iMn--4FNugtH26G90N1MaQ).

## Tracking idea

[Why use chain of responsibility tracking?](https://dylancaicoding.github.io/Tracker/#/zh/idea)

## Gradle

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
    implementation 'com.github.DylanCaiCoding:Tracker:1.0.1'
}
```

## Usage

:pencil: **[>> Usage documentation <<](https://dylancaicoding.github.io/Tracker/#/zh/usage)**

## Sample

Set a `trackNode` for the Activity/Fragment/View to add tracking parameters.

```kotlin
trackNode = TrackNode("channel_name" to "recommend")
```

```kotlin
holder.itemView.trackNode = TrackNode("video_id" to item.id, "video_type" to item.type)
```

Set a referrer node and a page node to establish a chain of source responsibilities between activity.

```kotlin
val intent = Intent(activity, DetailsActivity::class.java).putReferrerTrackNode(view)
activity.startActivity(intent)
```

```kotlin
activity.trackNode = PageTrackNode("page_name" to "details")
```

This creates a chain of responsibility similar to the one below.

![image](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e4056fcff5a84c75988f4fa60e7e6ab5~tplv-k3u1fbpfcp-zoom-1.image)

Then it can collect and post the parameters on the responsibility chain through any view.

```kotlin
view.postTrack("click_favorite")
```

This library has Kotlin and Java sample code for simulating Buzzvideo tracking requirements.

## Change log

[Releases](https://github.com/DylanCaiCoding/Tracker/releases)

## Author's other libraries

| Library                                                      | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [Longan](https://github.com/DylanCaiCoding/Longan)           | Probably the best Kotlin utils library for Android.       |
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
