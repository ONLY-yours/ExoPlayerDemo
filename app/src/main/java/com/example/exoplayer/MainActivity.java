package com.example.exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.exoplayer.mediaplayer.ExoPlayerManger;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;

public class MainActivity extends AppCompatActivity {


    public PlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
    }

    void initview(){
        playerView = findViewById(R.id.videoview);

        ExoPlayerManger exoPlayerManger = new ExoPlayerManger();
        exoPlayerManger.setBuilderContext(this);
        //设置Uri
//        exoPlayerManger.setVideoUrl(playVideoUrl);
        //设置从raw下读取的文件路径
        exoPlayerManger.setVideoUrl(RawResourceDataSource.buildRawResourceUri(R.raw.media_test).toString());

        SimpleExoPlayer simpleExoPlayer = exoPlayerManger.create();
        //设置音量
        simpleExoPlayer.setVolume(10);
        simpleExoPlayer.setVolume(0);
//        simpleExoPlayer.setRepeatMode(1);

        playerView.setPlayer(simpleExoPlayer);
        //监听（可自定义拓展）
//        simpleExoPlayer.addListener(this);
        //开启播放
        simpleExoPlayer.setPlayWhenReady(true);
    }

}