# Tracker

[English](https://github.com/DylanCaiCoding/Tracker) | 中文

[![](https://www.jitpack.io/v/DylanCaiCoding/Tracker.svg)](https://www.jitpack.io/#DylanCaiCoding/Tracker)
[![](https://img.shields.io/badge/License-Apache--2.0-blue.svg)](https://github.com/DylanCaiCoding/Tracker/blob/master/LICENSE)

Tracker 是基于[西瓜视频的责任链埋点思路](https://mp.weixin.qq.com/s/iMn--4FNugtH26G90N1MaQ)实现的轻量级埋点框架。个人理解其核心思想后进行了改进和优化，最后仅用了 200 多行代码实现，学习成本更低，并且兼顾了 Kotlin 和 Java 用法。

## 埋点思路

[为什么使用责任链的埋点方案？](https://dylancaicoding.github.io/Tracker/#/zh/idea)

## Gradle

在根目录的 build.gradle 添加：

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://www.jitpack.io' }
    }
}
```

添加依赖：

```groovy
dependencies {
    implementation 'com.github.DylanCaiCoding:Tracker:1.0.1'
}
```

## 用法

:pencil: **[>> 使用文档 <<](https://dylancaicoding.github.io/Tracker/#/zh/usage)**

## 示例

给 Activity、Fragment、View 设置埋点节点，通过视图树的层级关系（比如：`Activity -> Fragment -> ViewHolder -> Button`）建立节点的上下级责任链关系。

```kotlin
trackNode = TrackNode("channel_name" to "recommend")
```

```kotlin
holder.itemView.trackNode = TrackNode("video_id" to item.id, "video_type" to item.type)
```

设置来源节点和页面节点建立页面间的来源关系。

```kotlin
val intent = Intent(activity, DetailsActivity::class.java).putReferrerTrackNode(view)
activity.startActivity(intent)
```

```kotlin
activity.trackNode = PageTrackNode("page_name" to "details")
```

这样就建立了类似下图的责任链。

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e4056fcff5a84c75988f4fa60e7e6ab5~tplv-k3u1fbpfcp-zoom-1.image)

后续就能通过任意控件去上报责任链上的埋点参数。

```kotlin
view.postTrack("click_favorite")
```

本库有模拟西瓜视频埋点需求的示例代码，大家可以克隆项目运行 `sample-java` 或 `sample-kotlin`，点击各个位置的收藏按钮查看埋点日志。

## 更新日志

[Releases](https://github.com/DylanCaiCoding/Tracker/releases)

## 反馈

有问题可以提 [issues](https://github.com/DylanCaiCoding/Tracker/issues) 或加个人微信 `DylanCaiCoding`直接反馈。

## 作者其它的库

| 库                                                           | 简介                                           |
| ------------------------------------------------------------ | ---------------------------------------------- |
| [Longan](https://github.com/DylanCaiCoding/Longan)           | 可能是最好用的 Kotlin 工具库      |
| [LoadingStateView](https://github.com/DylanCaiCoding/LoadingStateView) | 深度解耦标题栏或加载中、加载失败、无数据等视图 |
| [ViewBindingKTX](https://github.com/DylanCaiCoding/ViewBindingKTX) | 最全面的 ViewBinding 工具                    |
| [MMKV-KTX](https://github.com/DylanCaiCoding/MMKV-KTX)       | 用属性委托的方式使用 MMKV                            |

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
