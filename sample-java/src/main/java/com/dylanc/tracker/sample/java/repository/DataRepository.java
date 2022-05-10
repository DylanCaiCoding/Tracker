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

package com.dylanc.tracker.sample.java.repository;

import com.dylanc.tracker.sample.java.bean.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan Cai
 */
public class DataRepository {

  private static final List<Video> zxcVideoList = new ArrayList<>();
  private static final List<Video> marvelVideoList = new ArrayList<>();

  static {
    zxcVideoList.add(new Video("101", "功夫", "1", "周星驰主演电影"));
    zxcVideoList.add(new Video("102", "少林足球", "1", "周星驰主演电影"));
    zxcVideoList.add(new Video("103", "大话西游", "1", "周星驰主演电影"));
    zxcVideoList.add(new Video("104", "大话西游2", "1", "周星驰主演电影"));
    zxcVideoList.add(new Video("105", "逃学威龙", "1", "周星驰主演电影"));
    zxcVideoList.add(new Video("106", "逃学威龙2", "1", "周星驰主演电影"));
    zxcVideoList.add(new Video("107", "逃学威龙3", "1", "周星驰主演电影"));

    marvelVideoList.add(new Video("111", "钢铁侠", "2", "漫威系列电影"));
    marvelVideoList.add(new Video("112", "钢铁侠2", "2", "漫威系列电影"));
    marvelVideoList.add(new Video("113", "钢铁侠3", "2", "漫威系列电影"));
    marvelVideoList.add(new Video("114", "美国队长", "2", "漫威系列电影"));
    marvelVideoList.add(new Video("115", "美国队长2", "2", "漫威系列电影"));
    marvelVideoList.add(new Video("116", "美国队长3内战", "2", "漫威系列电影"));
    marvelVideoList.add(new Video("117", "复仇者联盟", "2", "漫威系列电影"));
    marvelVideoList.add(new Video("118", "复仇者联盟2", "2", "漫威系列电影"));
    marvelVideoList.add(new Video("119", "复仇者联盟3", "2", "漫威系列电影"));
    marvelVideoList.add(new Video("120", "复仇者联盟4", "2", "漫威系列电影"));
  }

  public static List<Video> getRecommendVideos() {
    ArrayList<Video> list = new ArrayList<>();
    list.add(new Video("100", "罗小黑战纪大电影", "3"));
    list.add(new Video("101", "功夫", "1", "周星驰主演电影"));
    list.add(new Video("102", "少林足球", "1", "周星驰主演电影"));
    list.add(new Video("111", "钢铁侠", "2", "漫威系列电影"));
    return list;
  }

  public static List<Video> getMovieVideos() {
    ArrayList<Video> list = new ArrayList<>();
    list.add(new Video("111", "钢铁侠", "2", "漫威系列电影"));
    list.add(new Video("112", "钢铁侠2", "2", "漫威系列电影"));
    list.add(new Video("113", "钢铁侠3", "2", "漫威系列电影"));
    list.add(new Video("105", "逃学威龙", "1", "周星驰主演电影"));
    list.add(new Video("106", "逃学威龙2", "1", "周星驰主演电影"));
    list.add(new Video("107", "逃学威龙3", "1", "周星驰主演电影"));
    return list;
  }

  public static List<Video> getSeriesMovies(String id) {
    for (Video video : zxcVideoList) {
      if (video.getId().equals(id)) {
        return zxcVideoList;
      }
    }
    for (Video video : marvelVideoList) {
      if (video.getId().equals(id)) {
        return marvelVideoList;
      }
    }
    return new ArrayList<>();
  }
}
