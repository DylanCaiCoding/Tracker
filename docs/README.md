# Tracker

[![](https://www.jitpack.io/v/DylanCaiCoding/Tracker.svg)](https://www.jitpack.io/#DylanCaiCoding/Tracker) 
[![](https://img.shields.io/badge/License-Apache--2.0-blue.svg)](https://github.com/DylanCaiCoding/Tracker/blob/master/LICENSE)
[![GitHub Repo stars](https://img.shields.io/github/stars/DylanCaiCoding/Tracker?style=social)](https://github.com/DylanCaiCoding/Tracker)

Tracker 是基于[西瓜视频的责任链埋点思路](https://mp.weixin.qq.com/s/iMn--4FNugtH26G90N1MaQ)实现的轻量级埋点框架。个人理解其核心思想后进行了改进和优化，最后仅用了 200 多行代码实现，学习成本更低，并且兼顾了 Kotlin 和 Java 用法。

## 埋点思路

[为什么使用责任链的埋点方案？](/zh/idea)

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

## 示例

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
