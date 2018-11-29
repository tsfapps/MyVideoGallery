package com.myvideo.myvideo.t_activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.myvideo.myvideo.R;

public class DetailActivity extends AppCompatActivity {

    private TextView textViewTitleDet;
    private TextView textViewDesDet;
    private ImageView imageViewDet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewTitleDet = findViewById(R.id.tv_title_detail);
        textViewDesDet = findViewById(R.id.tv_des_detail);
       // imageViewDet = findViewById(R.id.iv_det_img);

        String title = getIntent().getExtras().getString("VID_TITLE");
      //  String thumb = getIntent().getExtras().getString("VID_THUMB");
        String description = getIntent().getExtras().getString("VID_DES");
        String LINK = getIntent().getExtras().getString("VID_URL");


      //  String  = "type_here_the_link";

        VideoView videoView = findViewById(R.id.video);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        Uri video = Uri.parse(LINK);
        videoView.setMediaController(mc);
        videoView.setVideoURI(video);
        videoView.start();

        textViewTitleDet.setText(title);
        textViewDesDet.setText(description);
       // Glide.with(this).load(thumb).into(imageViewDet);
    }
}
