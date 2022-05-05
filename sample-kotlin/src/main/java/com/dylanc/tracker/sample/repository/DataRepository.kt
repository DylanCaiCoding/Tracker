package com.dylanc.tracker.sample.repository

import com.dylanc.tracker.sample.bean.Video

object DataRepository {
  private val zxcVideoList = listOf(
    Video("101", "功夫", "周星驰主演电影"),
    Video("102", "少林足球", "周星驰主演电影"),
    Video("103", "大话西游", "周星驰主演电影"),
    Video("104", "大话西游2", "周星驰主演电影"),
    Video("105", "逃学威龙", "周星驰主演电影"),
    Video("106", "逃学威龙2", "周星驰主演电影"),
    Video("107", "逃学威龙3", "周星驰主演电影"),
  )

  private val marvelVideoList = listOf(
    Video("111", "钢铁侠", "漫威系列电影"),
    Video("112", "钢铁侠2", "漫威系列电影"),
    Video("113", "钢铁侠3", "漫威系列电影"),
    Video("114", "美国队长", "漫威系列电影"),
    Video("115", "美国队长2", "漫威系列电影"),
    Video("116", "美国队长3内战", "漫威系列电影"),
    Video("117", "复仇者联盟", "漫威系列电影"),
    Video("118", "复仇者联盟2", "漫威系列电影"),
    Video("119", "复仇者联盟3", "漫威系列电影"),
    Video("120", "复仇者联盟4", "漫威系列电影"),
  )

  val recommendVideoList = listOf(
    Video("100", "罗小黑战纪大电影"),
    Video("101", "功夫", "周星驰主演电影"),
    Video("102", "少林足球", "周星驰主演电影"),
    Video("111", "钢铁侠", "漫威系列电影"),
  )

  val movieVideoList = listOf(
    Video("111", "钢铁侠", "漫威系列电影"),
    Video("112", "钢铁侠2", "漫威系列电影"),
    Video("113", "钢铁侠3", "漫威系列电影"),
    Video("105", "逃学威龙", "周星驰主演电影"),
    Video("106", "逃学威龙2", "周星驰主演电影"),
    Video("107", "逃学威龙3", "周星驰主演电影"),
  )

  fun getSeriesMovies(id: String): List<Video> =
    when {
      zxcVideoList.any { it.id == id } -> zxcVideoList
      marvelVideoList.any { it.id == id } -> marvelVideoList
      else -> emptyList()
    }
}