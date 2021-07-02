package com.example.mvp.demo;


import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkPlayDemo {

    private IjkMediaPlayer mIjkMediaPlayer;

    public void PlayVideo(Context context) {
        //xml新建SurfaceView
        SurfaceView surfaceView = new SurfaceView(context);
        mIjkMediaPlayer = new IjkMediaPlayer();

        try {
            mIjkMediaPlayer.setDataSource("http://video.pearvideo.com/mp4/adshort/20210622/cont-1732869-15701252_adpkg-ad_hd.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                mIjkMediaPlayer.setDisplay(holder);
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
        mIjkMediaPlayer.prepareAsync();
        mIjkMediaPlayer.start();
    }
}
