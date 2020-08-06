package com.example.exoplayer.mediaplayer;

import android.content.Context;
import android.os.Environment;

import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;

/**
 * @author ：leo on 2018/12/17 17:58
 * <p>
 * 方法用途 ：视频缓存单例模式
 */
public class VideoCache {
    private static SimpleCache sDownloadCache;

    /**
     * @param context
     * @return
     */
    public static SimpleCache getInstance(Context context) {
        if (sDownloadCache == null) {
            sDownloadCache = new SimpleCache(new File(getMediaCacheFile(context), "StoryCache"), new LeastRecentlyUsedCacheEvictor(512 * 1024 *1024));

        }
        return sDownloadCache;
    }

    public static File getMediaCacheFile(Context context) {
        String directoryPath = "";
        String childPath = "exoPlayer";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 外部储存可用
            directoryPath = File.separator + context.getExternalFilesDir(childPath).getAbsolutePath();
        } else {
            directoryPath = File.separator + context.getFilesDir().getAbsolutePath() + File.separator + childPath;
        }
        File file = new File(directoryPath);
        //判断文件目录是否存在
        if (!file.exists()) {
            file.mkdirs();
        }

        return file;
    }


}
