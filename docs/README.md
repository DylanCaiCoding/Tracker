# Tracker

Tracker 是基于[西瓜视频责任链的埋点思路](https://mp.weixin.qq.com/s/iMn--4FNugtH26G90N1MaQ)实现的轻量级埋点框架。个人理解了核心思想后根据自己的理解进行改进和优化，最后使用了不到 200 行代码（不含注释）实现，整体更加简洁易用，同时兼顾了 Kotlin 和 Java 的用法。

## 埋点思路

- [为什么使用责任链的埋点方案？](/idea)

## 示例

本库有模拟西瓜视频埋点需求的示例代码，大家可以克隆项目运行 `sample-java` 或 `sample-kotlin`，点击各个位置的收藏按钮查看埋点日志。

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
    implementation 'com.github.DylanCaiCoding:Track:1.0.0'
}
```

### 开始使用

[Java 用法](/java)请查看文档。

#### 初始化

在 Application 初始化，传入一个 `TrackHandler` 实例。

```kotlin
initTracker(this, UMTrackHandler())
```

```kotlin
class UMTrackHandler : TrackHandler {
  override fun onEvent(context: Context, eventId: String, params: Map<String, String>) {
    MobclickAgent.onEvent(context, eventId, params) // 以友盟统计为例
  }
}
```

#### 建立页面上下级责任链

给 Activity、Fragment 或 View 设置埋点节点 `trackNode` 添加埋点参数。

```kotlin
trackNode = TrackNode("channel_name" to "recommend")
```

```kotlin
holder.itemView.trackNode = TrackNode("video_id" to item.id)
```

通过视图树的层级关系（比如：`Activity -> Fragment -> ViewHolder -> Button`）就能建立节点的上下级责任链关系。

#### 建立页面来源责任链

页面跳转时需要调用 `intent.setReferrerTrackNode(activity/fragment/view)` 设置来源节点。

```kotlin
val intent = Intent(activity, DetailsActivity::class.java).setReferrerTrackNode(view)
activity.startActivity(intent)
```

然后在跳转的 Activity 设置一个页面节点 `PageTrackNode`，这样就建立了页面间的来源责任链。

```kotlin
trackNode = PageTrackNode("page_name" to "details")
```

`PageTrackNode` 会将前面所有节点的参数添加进埋点中，添加的时候可以设置一些转换规则。比如上个页面的 `page_name`，跳转后上报 `from_page`。

```kotlin
val referrerKeyMap = mapOf("page_name" to "from_page", "channel_name" to "from_channel_name")
trackNode = PageTrackNode(referrerKeyMap, "page_name" to "details")
```

#### 上报埋点参数

调用 `Activity/Fragment/View.postTrack(eventId)` 会通过上下级责任链和来源责任链收集埋点参数，回调给 `TrackHandler` 进行上报。

```kotlin
view.postTrack("click_favorite")
```

#### 线索节点

可在 Activity 设置线索节点，线索节点能在 View 或页面之间共享埋点参数。

```kotlin
class RecordThreadNode : TrackNode {
  var isRecord = false

  override fun fillTackParams(params: TrackParams) {
    params.put("is_record", isRecord)
  }
}

putTrackThreadNode(RecordThreadNode())
```

之后就能在 Activity、Fragment、View 更新线索节点中的参数。

```kotlin
view.getTrackThreadNode<RecordThreadNode>()?.isRecord = true
```

上报的时候需要对线索节点进行声明才会收集参数。

```kotlin
view.postTrack("click_favorite", RecordThreadNode::class.java)
```

线索节点适合用于具有会话特性的流程中，方便在流程中共享参数，常见的有登录、注册的流程，订单创建流程等。


## 作者其它的库

| 库                                                           | 简介                                           |
| ------------------------------------------------------------ | ---------------------------------------------- |
| [Longan](https://github.com/DylanCaiCoding/Longan)           | 可能是最好用的 Kotlin 工具库      |
| [LoadingStateView](https://github.com/DylanCaiCoding/LoadingStateView) | 深度解耦标题栏或加载中、加载失败、无数据等视图 |
| [ViewBindingKTX](https://github.com/DylanCaiCoding/ViewBindingKTX) | 最全面的 ViewBinding 工具                    |
| [MMKV-KTX](https://github.com/DylanCaiCoding/MMKV-KTX)       | 用属性委托的方式使用 MMKV                            |

## License

```
Copyright (C) 2021. Dylan Cai

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
