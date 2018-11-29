package com.myvideo.myvideo.t_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myvideo.myvideo.R;
import com.myvideo.myvideo.t_model.VideoModel;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.videoViewHolder> {

    private Context tContext;
    private List<VideoModel> videoModels;
    private onVideoClkListener mListner;

    public interface onVideoClkListener {
        void onItmClk(int position);
    }

    public void setOnVidClk(onVideoClkListener listener){
        mListner = listener;
    }



    public VideoAdapter(Context tContext, List<VideoModel> videoModels) {
        this.tContext = tContext;
        this.videoModels = videoModels;
    }

    @NonNull
    @Override
    public videoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_list, viewGroup, false);

        return new videoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull videoViewHolder videoViewHolder, int i) {

       final VideoModel videoModel = videoModels.get(i);

       videoViewHolder.textViewTitle.setText(videoModel.getTitle());
        Glide.with(tContext).load(videoModel.getThumb()).into(videoViewHolder.imageViewThumb);



    }

    @Override
    public int getItemCount() {
        return videoModels.size();
    }

    public class videoViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageViewThumb;
        public TextView textViewTitle;

        public videoViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewThumb = itemView.findViewById(R.id.iv_thumb);
            textViewTitle = itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mListner != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListner.onItmClk(position);
                        }

                    }

                }
            });


        }
    }

}
