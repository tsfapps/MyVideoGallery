package com.myvideo.myvideo.t_model;

public class VideoModel {


 private String description;
 private String thumb;
 private String title;
 private String url;
 private String user_name;
 private String user_image;


    //    public VideoModel(int id, String description, String thumb, String title, String url) {
//        this.id = id;
//        this.description = description;
//        this.thumb = thumb;
//        this.title = title;
//        this.url = url;
//    }


    public String getDescription() {
        return description;
    }

    public String getThumb() {
        return thumb;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
