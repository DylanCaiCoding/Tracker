### 初始化

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

### 建立页面上下级责任链

给 Activity、Fragment 或 View 设置埋点节点 `trackNode` 添加埋点参数。

```kotlin
trackNode = TrackNode("channel_name" to "recommend")
```

```kotlin
holder.itemView.trackNode = TrackNode("video_id" to item.id)
```

通过视图树的层级关系（比如：`Activity -> Fragment -> ViewHolder -> Button`）就能建立节点的上下级责任链关系。

### 建立页面来源责任链

页面跳转时需要调用 `intent.setReferrerTrackNode(activity/fragment/view)` 设置来源节点。

```kotlin
val intent = Intent(activity, DetailsActivity::class.java).setReferrerTrackNode(view)
activity.startActivity(intent)
```

然后在跳转的 Activity 设置一个页面节点 `PageTrackNode`，这样就建立了页面间的来源责任链。

```kotlin
trackNode = PageTrackNode("page_name" to "details")
```

`PageTrackNode` 会将前面节点的所有参数添加进埋点中，添加的时候可以设置一些转换规则。比如上个页面的 `page_name`，跳转后上报 `from_page`。

```kotlin
val referrerKeyMap = mapOf("page_name" to "from_page", "channel_name" to "from_channel_name")
trackNode = PageTrackNode(referrerKeyMap, "page_name" to "details")
```

### 上报埋点参数

调用 `Activity/Fragment/View.postTrack(eventId)` 会通过上下级责任链和来源责任链收集埋点参数，回调给 `TrackHandler` 进行上报。

```kotlin
view.postTrack("click_favorite")
```

### 线索节点

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