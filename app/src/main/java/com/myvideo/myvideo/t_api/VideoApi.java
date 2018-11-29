package com.myvideo.myvideo.t_api;

import com.myvideo.myvideo.t_model.VideoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VideoApi {


    @GET("media.json")
    Call<List<VideoModel>> getVideo();

}
