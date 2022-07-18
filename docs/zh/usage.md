# 用法

## 初始化

在 Application 初始化，传入一个 `TrackHandler` 实例。

<!-- tabs:start -->

#### **Kotlin**

```kotlin
initTracker(UMTrackHandler())
```

```kotlin
class UMTrackHandler : TrackHandler {
  override fun onEvent(context: Context, eventId: String, params: Map<String, String>) {
    MobclickAgent.onEvent(context, eventId, params) // 以友盟统计为例
  }
}
```

#### **Java**

```java
Tracker.init(this, new UMTrackHandler());
```

```java
public class UMTrackHandler implements TrackHandler {

  @Override
  public void onEvent(@NonNull Context context, @NonNull String eventId, @NonNull Map<String, String> params) {
    MobclickAgent.onEvent(context, eventId, params); // 以友盟统计为例
  }
}
```

<!-- tabs:end -->

## 建立页面内上下级责任链

可以给 Activity、Fragment、View 设置埋点节点 `TrackNode` 添加埋点参数。

<!-- tabs:start -->

#### **Kotlin**

```kotlin
trackNode = TrackNode("channel_name" to "recommend")
```

```kotlin
holder.itemView.trackNode = TrackNode("video_id" to item.id, "video_type" to item.type)
```

#### **Java**

```java
Tracker.setTrackNode(this, params -> params.put("channel_name", "recommend"));
```

```java
Tracker.setTrackNode(holder.itemView, params -> params.put("video_id", item.getId()).put("video_type", item.getType()));
```

<!-- tabs:end -->

通过视图树的层级关系（比如：`Activity -> Fragment -> ViewHolder -> Button`）就能建立节点的上下级责任链关系。

## 建立页面来源责任链

页面跳转时需要设置来源节点。

<!-- tabs:start -->

#### **Kotlin**

```kotlin
val intent = Intent(activity, DetailsActivity::class.java).putReferrerTrackNode(view)
activity.startActivity(intent)
```

#### **Java**

```java
Intent intent = new Intent(activity, DetailsActivity.class);
Tracker.putReferrerTrackNode(intent, view);
activity.startActivity(intent);
```

<!-- tabs:end -->

然后在跳转的 Activity 设置一个页面节点 `PageTrackNode`，这样就建立了页面间的来源责任链。

<!-- tabs:start -->

#### **Kotlin**

```kotlin
trackNode = PageTrackNode("page_name" to "details")
```

#### **Java**

```java
Tracker.setPageTrackNode(this, params -> params.put("page_name", "details"));
```

<!-- tabs:end -->

`PageTrackNode` 会添加前面所有节点的参数，添加的时候可以设置一些转换规则。比如上个页面的 `page_name`，跳转后上报 `from_page`。

<!-- tabs:start -->

#### **Kotlin**

```kotlin
val referrerKeyMap = mapOf("page_name" to "from_page", "channel_name" to "from_channel_name")
trackNode = PageTrackNode(referrerKeyMap, "page_name" to "details")
```

#### **Java**

```java
HashMap<String, String> referrerKeyMap = new HashMap<>();
referrerKeyMap.put("page_name", "from_page");
referrerKeyMap.put("channel_name", "from_channel_name");
Tracker.setPageTrackNode(this, referrerKeyMap, params -> params.put("page_name", "details"));
```

<!-- tabs:end -->

## 上报埋点参数

上报埋点会通过上下级责任链和来源责任链收集埋点参数，回调给 `TrackHandler` 进行上报。

<!-- tabs:start -->

#### **Kotlin**

```kotlin
view.postTrack("click_favorite")
```

#### **Java**

```java
Tracker.postTrack(view, "click_favorite");
```

<!-- tabs:end -->

## 线索节点

线索节点适合用于具有会话特性的流程中，方便在流程中共享参数，常见的有登录、注册的流程、订单创建流程等。

在 Activity 可以设置线索节点，线索节点能在 View 或页面之间共享埋点参数。

<!-- tabs:start -->

#### **Kotlin**

```kotlin
class RecordTrackNode : TrackNode {
  var isRecord = false

  override fun fillTackParams(params: TrackParams) {
    params.put("is_record", it)
  }
}
```

```kotlin
activity.putThreadTrackNode(RecordTrackNode())
```

#### **Java**

```java
public class RecordTrackNode implements TrackNode {

  public boolean isRecord = false;

  @Override
  public void fillTackParams(@NonNull TrackParams params) {
    params.put("is_record", isRecord);
  }
}
```

```java
Tracker.putThreadTrackNode(activity, new RecordTrackNode());
```

<!-- tabs:end -->

之后就能在 Activity、Fragment、View 更新线索节点中的参数。

<!-- tabs:start -->

#### **Kotlin**

```kotlin
view.updateThreadTrackNode<RecordTrackNode> { isRecord = true }
```

#### **Java**

```java
Tracker.updateThreadTrackNode(view, RecordTrackNode.class, node -> node.isRecord = true);
```

<!-- tabs:end -->

上报的时候需要对线索节点进行声明才会收集参数。

<!-- tabs:start -->

#### **Kotlin**

```kotlin
view.postTrack("click_publish", RecordTrackNode::class.java)
```

#### **Java**

```java
Tracker.postTrack(view, "click_publish", RecordTrackNode.class);
```

<!-- tabs:end -->