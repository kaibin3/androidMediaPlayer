package com.example.wenjie.mediaplayerdm.PhiFind;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.wenjie.mediaplayerdm.R;
import com.example.wenjie.mediaplayerdm.util.Constants;

import java.io.IOException;

/**
 * Created by wen.jie on 2018/1/3.
 */

public class VideoPlayView extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = "VideoPlayView";
    private MediaInfo mediaInfo;
    private String videoUrl;
    private String PhotoUrl;

    private SurfaceView mSurfaceView;
    private ImageView mPhotoImg;
    private ImageView mPlayImg;

    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;

    public VideoPlayView(Context context) {
        super(context);
        init();
    }

    public VideoPlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.video_play_view, this);
        mPhotoImg = findViewById(R.id.video_previous_img);
        mPlayImg = findViewById(R.id.play_img);
        mPlayImg.setOnClickListener(this);
        mSurfaceView = findViewById(R.id.surface_view);
    }


    private void initPlayer() {
        Log.d(TAG, "initPlayer: ");
        mMediaPlayer = new MediaPlayer();
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                onSurfaceCreated(holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });

    }


    private void onSurfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "onSurfaceCreated: ");
        mMediaPlayer.setDisplay(holder);
        //网络视频
        String videoUrl2 = Constants.phiVideoUrl;
        Uri uri = Uri.parse(videoUrl2);

        try {
            mMediaPlayer.setDataSource(uri.toString());
            mMediaPlayer.prepare();

        } catch (IOException e) {
            Log.e(TAG, "onSurfaceCreated: " + e);
            e.printStackTrace();
        }

    }

    private void startPlay() {
        mPhotoImg.setVisibility(View.INVISIBLE);
        mPlayImg.setVisibility(View.INVISIBLE);
        mMediaPlayer.start();
    }


    private void stopPlay() {
        mMediaPlayer.stop();
    }


    public void setMediaInfo(MediaInfo mediaInfo) {
        this.mediaInfo = mediaInfo;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_img:
                startPlay();
                break;
        }
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        initPlayer();
    }

    public void setPhotoUrl(String photoUrl) {
        PhotoUrl = photoUrl;
        loadPhoto();
    }

    private void loadPhoto() {
        mPhotoImg.setVisibility(View.VISIBLE);
        Glide.with(getContext()).load(PhotoUrl).into(mPhotoImg);
    }

    public class MediaInfo {
        String videoUrl;
        String PhotoUrl;
    }

}