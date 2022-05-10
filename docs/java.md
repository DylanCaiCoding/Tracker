## Java 用法

### 初始化

在 Application 初始化，传入一个 `TrackHandler` 实例。

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

### 建立页面内上下级责任链

可以给 Activity、Fragment、View 设置埋点节点 `trackNode` 添加埋点参数。

```java
Tracker.setTrackNode(this, params -> params.put("channel_name", "recommend"));
```

```java
Tracker.setTrackNode(holder.itemView, params -> params.put("video_id", item.getId()).put("video_type", item.getType()));
```

通过视图树的层级关系（比如：`Activity -> Fragment -> ViewHolder -> Button`）就能建立节点的上下级责任链关系。

### 建立页面来源责任链

页面跳转时需要调用 `Tracker.putReferrerTrackNode(intent, activity/fragment/view)` 设置来源节点。

```java
Intent intent = new Intent(activity, DetailsActivity.class);
Tracker.putReferrerTrackNode(intent, view);
activity.startActivity(intent);
```

然后在跳转的 Activity 设置一个页面节点 `PageTrackNode`，这样就建立了页面间的来源责任链。

```java
Tracker.setPageTrackNode(this, params -> params.put("page_name", "details"));
```

`PageTrackNode` 会添加前面所有节点的参数，添加的时候可以设置一些转换规则。比如上个页面的 `page_name`，跳转后上报 `from_page`。

```java
HashMap<String, String> referrerKeyMap = new HashMap<>();
referrerKeyMap.put("page_name", "from_page");
referrerKeyMap.put("channel_name", "from_channel_name");
Tracker.setPageTrackNode(this, referrerKeyMap, params -> params.put("page_name", "details"));
```

### 上报埋点参数

调用 `Tracker.postTrack(activity/fragment/view, eventId)` 会通过上下级责任链和来源责任链收集埋点参数，回调给 `TrackHandler` 进行上报。

```java
Tracker.postTrack(view, "click_favorite")
```

### 线索节点

线索节点适合用于具有会话特性的流程中，方便在流程中共享参数，常见的有登录、注册的流程、订单创建流程等。

在 Activity 可以设置线索节点，线索节点能在 View 或页面之间共享埋点参数。

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
Tracker.putThreadTrackNode(new RecordTrackNode());
```

之后可以在 Activity、Fragment、View 更新线索节点中的参数。

```java
Tracker.updateThreadTrackNode(v, RecordTrackNode.class, node -> node.isRecord = true);
```

上报的时候需要对线索节点进行声明才会收集参数。

```java
Tracker.postTrack(view, "click_publish", RecordTrackNode.class);
```