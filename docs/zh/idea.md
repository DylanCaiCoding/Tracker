# 责任链埋点思路

## 埋点需求

行为分析埋点通常需要包括某一事件发生时的前因、后果，以及事件发生对象的特征。在复杂的数据分析、模型训练等需求中，不仅仅需要获知某个事件的发生次数，对埋点上下文尤为关注。此处上下文指的通常有 2 类，分别是：

-   事件发生的页面信息和页面位置信息；
-   用户经过怎样的路径来到当前页面，也就是“来源”信息；

比如西瓜视频点击收藏的埋点场景，要求包含收藏影片的信息，所在的场景信息等。

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/85f841476fd74b33ad4caa095bc2ceea~tplv-k3u1fbpfcp-zoom-1.image)

1.  如果收藏事件发生在列表页，会上报如下的内容：

```
{
  "event": "click_favorite",
  "params": {
    "video_id": "123",                  // 影片ID
    "video_type": 2,                    // 影片类型
    "page_name": "feed",                // 当前页面
    "tab_name": "long_video"            // 当前所在的底Tab
    "channel_name": "lvideo_recommend", // 当前所在的频道
  }
}
```

2.  如果收藏事件发生在详情页，会上报如下的内容：

```
{
  "event": "click_favorite",
  "params": {
    "video_id": "123",                       // 影片ID
    "video_type": 2,                         // 影片类型
    "page_name": "detail",                   // 当前页面
    "from_page": "feed",                     // 来源页面
    "from_tab_name": "long_video"            // 来源底Tab
    "from_channel_name": "lvideo_recommend", // 来源频道
  }
}
```

## 现有方案

### 直接传参

通过平台支持的参数传递方式，逐个定义并且读写参数。直接传参有非常显著的缺陷：

- 每增加一个参数，都需要写大量的重复代码，工程代码膨胀；
- 模块间约定了很多埋点参数的协议，耦合程度高，难以维护；
- 一些场景的嵌套层次深，经过很多层的参数传递，非常容易漏报埋点参数；

### 单例传参

通过一个单例进行埋点参数的维护，程序中的任何位置都能方便地读和写埋点参数。这种方式带来的好处是不需要在每个类都定义大量的埋点参数，只需要访问单例进行修改和读取。会比前面的直接传参更简单，但这种方案治标不治本，同样有明显的弊端：

- 无法解决列表页这种多实例场景的问题，比如一个推荐列表中有多个卡片，每个卡片的埋点参数都不一样，卡片的埋点参数还是需要自己传；
- 单例的数据可能被多个位置写入，且一旦被覆盖就没法恢复，导致埋点参数上报错误；
- 存放和清理的时机难以控制，清理早了会导致埋点参数缺失，忘记清理可能导致后面的埋点获取不到参数；

### 全埋点/无埋点

指埋点 SDK 通过编译时插桩、运行时反射或动态代理的方式，自动进行埋点事件的触发和上报，理论上能够搜集到所有页面、视图的曝光、点击等事件，无须客户端工程师手动进行埋点开发工作。理想很丰满，现实很骨感，看似很完美的方案也是有些弊端：

- 仅能上报有限的简单事件类型，无法完成复杂事件的上报；
- 全场景的数据上报，可能产生大量的无用数据，消耗大量传输、存储、计算资源；
- 把复杂度从开发转嫁给了产品经理、数据分析师，消费成本较高；

## 西瓜视频的责任链方案

分析数据与视图节点的关系可以发现，埋点参数恰好就分布在视图树的责任链中。

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/2f54c6499d214e668c50a116f442af45~tplv-k3u1fbpfcp-zoom-1.image)

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c777c72524ee4fd3b75b7cbb05ab967f~tplv-k3u1fbpfcp-zoom-1.image)

结合跳转链路，逻辑上也是个树状结构。

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4906defb017f474391880c5399951459~tplv-k3u1fbpfcp-zoom-1.image)

![图片](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e4056fcff5a84c75988f4fa60e7e6ab5~tplv-k3u1fbpfcp-zoom-1.image)

所以我们需要的埋点上下文参数，理论上都可以通过节点的关系找到，然后通过责任链能很方便地收集到埋点参数。

## 各方案的优缺点

现有的三种埋点方案都有明显的缺点，全埋点或无埋点看似很美好，却只是个半自动方案，能自动上报的只有简单事件，复杂的事件只能手动处理，这又回到了直接传参或单例传参。

个人不推荐单例传参，因为不太可控，可能会被覆盖，清理时机不好把控，清早了丢数据。

直接传参是最稳的，但是会有大量的重复代码，并且嵌套过深可能会漏传参数。

而西瓜视频的责任链方案是直接传参的一种升级版，也是会传递参数，不过通过视图树和跳转链路建立的责任链自动收集埋点参数，代码量远比直接传参少很多。该方案也能作为全埋点或者无埋点的一种补充。

## 参考文章

- [《西瓜客户端埋点实践：基于责任链的埋点框架》](https://mp.weixin.qq.com/s/iMn--4FNugtH26G90N1MaQ)
