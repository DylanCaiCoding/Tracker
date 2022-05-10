/*
 * Copyright (c) 2022. Dylan Cai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dylanc.tracker.sample.repository

import com.dylanc.tracker.sample.bean.Video

object DataRepository {
  private val zxcVideoList = listOf(
    Video("101", "功夫", "1", "周星驰主演电影"),
    Video("102", "少林足球", "1", "周星驰主演电影"),
    Video("103", "大话西游", "1", "周星驰主演电影"),
    Video("104", "大话西游2", "1", "周星驰主演电影"),
    Video("105", "逃学威龙", "1", "周星驰主演电影"),
    Video("106", "逃学威龙2", "1", "周星驰主演电影"),
    Video("107", "逃学威龙3", "1", "周星驰主演电影"),
  )

  private val marvelVideoList = listOf(
    Video("111", "钢铁侠", "2", "漫威系列电影"),
    Video("112", "钢铁侠2", "2", "漫威系列电影"),
    Video("113", "钢铁侠3", "2", "漫威系列电影"),
    Video("114", "美国队长", "2", "漫威系列电影"),
    Video("115", "美国队长2", "2", "漫威系列电影"),
    Video("116", "美国队长3内战", "2", "漫威系列电影"),
    Video("117", "复仇者联盟", "2", "漫威系列电影"),
    Video("118", "复仇者联盟2", "2", "漫威系列电影"),
    Video("119", "复仇者联盟3", "2", "漫威系列电影"),
    Video("120", "复仇者联盟4", "2", "漫威系列电影"),
  )

  val recommendVideoList = listOf(
    Video("100", "罗小黑战纪大电影", "3"),
    Video("101", "功夫", "1", "周星驰主演电影"),
    Video("102", "少林足球", "1", "周星驰主演电影"),
    Video("111", "钢铁侠", "2", "漫威系列电影"),
  )

  val movieVideoList = listOf(
    Video("111", "钢铁侠", "2", "漫威系列电影"),
    Video("112", "钢铁侠2", "2", "漫威系列电影"),
    Video("113", "钢铁侠3", "2", "漫威系列电影"),
    Video("105", "逃学威龙", "1", "周星驰主演电影"),
    Video("106", "逃学威龙2", "1", "周星驰主演电影"),
    Video("107", "逃学威龙3", "1", "周星驰主演电影"),
  )

  fun getSeriesMovies(id: String): List<Video> =
    when {
      zxcVideoList.any { it.id == id } -> zxcVideoList
      marvelVideoList.any { it.id == id } -> marvelVideoList
      else -> emptyList()
    }
}