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
    zxcVideoList.add(new Video("101", "功夫", "周星驰主演电影"));
    zxcVideoList.add(new Video("102", "少林足球", "周星驰主演电影"));
    zxcVideoList.add(new Video("103", "大话西游", "周星驰主演电影"));
    zxcVideoList.add(new Video("104", "大话西游2", "周星驰主演电影"));
    zxcVideoList.add(new Video("105", "逃学威龙", "周星驰主演电影"));
    zxcVideoList.add(new Video("106", "逃学威龙2", "周星驰主演电影"));
    zxcVideoList.add(new Video("107", "逃学威龙3", "周星驰主演电影"));

    marvelVideoList.add(new Video("111", "钢铁侠", "漫威系列电影"));
    marvelVideoList.add(new Video("112", "钢铁侠2", "漫威系列电影"));
    marvelVideoList.add(new Video("113", "钢铁侠3", "漫威系列电影"));
    marvelVideoList.add(new Video("114", "美国队长", "漫威系列电影"));
    marvelVideoList.add(new Video("115", "美国队长2", "漫威系列电影"));
    marvelVideoList.add(new Video("116", "美国队长3内战", "漫威系列电影"));
    marvelVideoList.add(new Video("117", "复仇者联盟", "漫威系列电影"));
    marvelVideoList.add(new Video("118", "复仇者联盟2", "漫威系列电影"));
    marvelVideoList.add(new Video("119", "复仇者联盟3", "漫威系列电影"));
    marvelVideoList.add(new Video("120", "复仇者联盟4", "漫威系列电影"));
  }

  public static List<Video> getRecommendVideos() {
    ArrayList<Video> list = new ArrayList<>();
    list.add(new Video("100", "罗小黑战纪大电影"));
    list.add(new Video("101", "功夫", "周星驰主演电影"));
    list.add(new Video("102", "少林足球", "周星驰主演电影"));
    list.add(new Video("111", "钢铁侠", "漫威系列电影"));
    return list;
  }

  public static List<Video> getMovieVideos() {
    ArrayList<Video> list = new ArrayList<>();
    list.add(new Video("111", "钢铁侠", "漫威系列电影"));
    list.add(new Video("112", "钢铁侠2", "漫威系列电影"));
    list.add(new Video("113", "钢铁侠3", "漫威系列电影"));
    list.add(new Video("105", "逃学威龙", "周星驰主演电影"));
    list.add(new Video("106", "逃学威龙2", "周星驰主演电影"));
    list.add(new Video("107", "逃学威龙3", "周星驰主演电影"));
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
