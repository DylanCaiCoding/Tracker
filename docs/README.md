# Tracker

Tracker 是基于西瓜视频责任链的埋点思路实现的轻量级埋点框架。个人理解了核心思想后根据自己的理解进行改进和优化，最后使用了不到 200 行代码（不含注释）实现，整体更加简洁易用，同时兼顾了 Kotlin 和 Java 的用法。

## 埋点思路




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