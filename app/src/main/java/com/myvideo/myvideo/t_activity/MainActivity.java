package com.myvideo.myvideo.t_activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.widget.Toast;
import android.widget.VideoView;

import com.myvideo.myvideo.R;
import com.myvideo.myvideo.t_adapter.VideoAdapter;
import com.myvideo.myvideo.t_api.ApiClient;
import com.myvideo.myvideo.t_api.VideoApi;
import com.myvideo.myvideo.t_model.VideoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements VideoAdapter.onVideoClkListener{

    public static String VID_TITLE= "VID_TITLE";
    public static String VID_THUMB ="VID_THUMB";
    public static String VID_URL ="VID_URL";
    public static String VID_DES ="VID_DES";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<VideoModel> videoModelList;
    private VideoAdapter videoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_mainList);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        setVideoList();

    }

    public void setVideoList(){

        VideoApi videoApi = ApiClient.getRetrofit().create(VideoApi.class);

        Call<List<VideoModel>> call = videoApi.getVideo();
        call.enqueue(new Callback<List<VideoModel>>() {
            @Override
            public void onResponse(Call<List<VideoModel>> call, Response<List<VideoModel>> response) {
                videoModelList = response.body();
                videoAdapter = new VideoAdapter(getApplicationContext(), videoModelList);
                recyclerView.setAdapter(videoAdapter);
                videoAdapter.setOnVidClk(MainActivity.this);
            }

            @Override
            public void onFailure(Call<List<VideoModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Response Failure", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String textList) {

                textList = textList.toLowerCase();
                List<VideoModel> myList = new ArrayList<>();

                for (VideoModel model : videoModelList){

                    String vid_name = model.getTitle().toLowerCase();
                    if (vid_name.contains(textList)){
                        myList.add(model);
                    }
                }

                videoAdapter.filterItem(myList);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItmClk(int position) {
        Intent detailIntent = new Intent(getApplicationContext(), DetailActivity.class);
        VideoModel clickedItem = videoModelList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString(VID_TITLE, clickedItem.getTitle());
        bundle.putString(VID_THUMB, clickedItem.getThumb());
        bundle.putString(VID_URL, clickedItem.getUrl());
        bundle.putString(VID_DES, clickedItem.getDescription());
        detailIntent.putExtras(bundle);
        startActivity(detailIntent);
    }
}
