package com.example.exoplayer.mediaplayer;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

public class ExoPlayerManger {
    private static final String TAG = "ExoPlayerManger";
    private Context mContext;
    private BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
    // 创建轨道选择工厂
    private TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
    // 创建轨道选择器实例
    private TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
    private SimpleExoPlayer simpleExoPlayer;
    private DataSource.Factory dataSourceFactory;
    private String mVideoUrl;
    private SimpleCache simpleCache;
    private Uri playVideoUri;
    private ExtractorMediaSource mediaSource;


    /**
     * @param context 传入context
     */
    public void setBuilderContext(Context context) {
        mContext = context;
        dataSourceFactory = new DefaultDataSourceFactory(mContext, "seyed");
    }

    /**
     * @param videoUrl 传入视频路径
     */
    public void setVideoUrl(String videoUrl) {
        this.mVideoUrl = videoUrl;
        simpleCache = VideoCache.getInstance(mContext);
        playVideoUri = Uri.parse(mVideoUrl);
    }


    /**
     * @return 返回exoPlayer对象
     */
    public SimpleExoPlayer create() {
        try {
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector);
            dataSourceFactory = new CacheDataSourceFactory(simpleCache, dataSourceFactory);
            mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(playVideoUri);
            simpleExoPlayer.prepare(mediaSource);

        } catch (Exception e) {

        }
        return simpleExoPlayer;
    }


}
