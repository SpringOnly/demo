package com.example.mvp.demo;

import android.Manifest;
import android.content.Context;
import android.view.SurfaceView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.example.mvp.util.Effect;
import com.example.mvp.view.AgoraActivity;

import java.util.ArrayList;

import io.agora.extension.ExtensionManager;
import io.agora.extension.ResourceHelper;
import io.agora.extension.UtilsAsyncTask;
import io.agora.rtc2.Constants;
import io.agora.rtc2.IRtcEngineEventHandler;
import io.agora.rtc2.RtcEngine;
import io.agora.rtc2.RtcEngineConfig;
import io.agora.rtc2.video.VideoCanvas;

public class AgoraDemo extends UtilsAsyncTask {


    public static final String appId = "babb67984093407596803d8bc444c236";
    public static String RoomName = "123";
    public static final String TempToken = "006babb67984093407596803d8bc444c236IADM7XnyS0NUExZxhh3g3Bj0rsorGfRUUggmOgJQI80m99JjSIgAAAAAEAB4I/xcd6UbYQEAAQB2pRth";
    //声网主要类
    static RtcEngine mRtcEngine;
    //声网 相芯的回调
//    private static FuaEventHandler mFuaEventHandler;
    private static OnUtilsAsyncTaskEvents mEvents;

    public AgoraDemo(Context context, OnUtilsAsyncTaskEvents events) {
        super(context, events);
        mEvents = events;
    }


    public static void init(Context context) {

        PermissionUtils.permission(Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void
                    onGranted() {
                        try {
                            initEngineAndJoinChannel(context);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    @Override
                    public void onDenied() {

                    }
                })
                .request();
    }


    //初始化 RtcEngine 加入频道
    private static void initEngineAndJoinChannel(Context context) throws Exception {
//        mFuaEventHandler = new FuaEventHandler();
        RtcEngineConfig config = new RtcEngineConfig();
        config.mContext = context;
        config.mAppId = appId;
//        config.mEventHandler = mFuaEventHandler;

        mRtcEngine = RtcEngine.create(config);
        //将频道场景设为直播。
        mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
        //视频通话功能
        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
        //设置本地视图
        setupLocalVideo(mRtcEngine, context);

        //加载相芯插件
        long provider = ExtensionManager.nativeGetExtensionProvider(context, ExtensionManager.VENDOR_NAME);
        //注册native provider句柄，其中：vender用于区分不同的插件，observer用于监听该插件的消息
//        config.addExtension(ExtensionManager.VENDOR_NAME, provider);
        //启用相芯插件
//        mRtcEngine.enableExtension(ExtensionManager.VENDOR_NAME, true);

        //如果本地文件没有这个鉴权证书 开始copy到本地文件夹里面
        if (!ResourceHelper.isResourceReady(context, 2)) {
            new UtilsAsyncTask(context, mEvents).execute();
        }

        ArrayList<Effect> filter = Effect.EffectEnum.getEffectsByKey("stickers");
        Effect effect = filter.get(1);

    }


    private static void setupLocalVideo(RtcEngine mRtcEngine, Context context) {
        //启用视频模块
        mRtcEngine.enableVideo();
        //启用音频模块
        mRtcEngine.enableAudio();

        SurfaceView localView = new SurfaceView(context);
        localView.setZOrderMediaOverlay(true);
//        Binding.localVideoViewContainer.addView(localView);
        VideoCanvas videoCanvas = new VideoCanvas(localView, VideoCanvas.RENDER_MODE_ADAPTIVE, 0);
        mRtcEngine.setupLocalVideo(videoCanvas);
        mRtcEngine.startPreview();
        joinChannel(mRtcEngine);
    }


    //加入频道
    private static void joinChannel(RtcEngine mRtcEngine) {
        mRtcEngine.joinChannel(TempToken, RoomName, "Extra Optional Data", 0);
    }


//    private static class FuaEventHandler extends IRtcEngineEventHandler implements IMediaExtensionObserver {
//
//        //vendor即为上述注册插件时的VENDOR_NAME，key/value是插件消息的键值对
//        //相芯插件的回调
//        @Override
//        public void onEvent(String vendor, String key, String value) {
////            LogUtils.e("vendor:" + vendor);
////            LogUtils.e("key:" + key);
////            LogUtils.e("value:" + value);
//        }
//
//        // 注册 onJoinChannelSuccess 回调。
//        // 本地用户成功加入频道时，会触发该回调。
//        @Override
//        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
//            super.onJoinChannelSuccess(channel, uid, elapsed);
//            LogUtils.e("onJoinChannelSuccess:" + uid);
//        }
//
//        //网络状态改变
//        @Override
//        public void onConnectionStateChanged(int state, int reason) {
//            super.onConnectionStateChanged(state, reason);
//        }
//
//        // 注册 onUserJoined 回调。
//        // 远端用户加入频道时时，会触发该回调。
//        // 可以在该回调中调用 setupRemoteVideo 方法设置远端视图。
//        @Override
//        public void onUserJoined(int uid, int elapsed) {
//            super.onUserJoined(uid, elapsed);
//            LogUtils.e("onUserJoined:" + uid);
////            runOnUiThread(() -> setupRemoteVideo(uid));
//        }
//
//        private void setupRemoteVideo(int uid) {
////            SurfaceView surfaceView = new SurfaceView(Binding.remoteVideoViewContainer.getContext());
////            Binding.remoteVideoViewContainer.removeAllViews();
////            Binding.remoteVideoViewContainer.addView(surfaceView);
////            VideoCanvas videoCanvas = new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, uid);
////            mRtcEngine.setupRemoteVideo(videoCanvas);
//        }
//
//        // 注册 onUserOffline 回调。
//        // 远端主播离开频道或掉线时，会触发该回调。
//        @Override
//        public void onUserOffline(int uid, int reason) {
//            super.onUserOffline(uid, reason);
//            LogUtils.e("agora", "User offline, uid: " + (uid & 0xFFFFFFFFL));
////            runOnUiThread(() -> onRemoteUserLeft(uid));
//        }
//
//        private void onRemoteUserLeft(int uid) {
////            Binding.remoteVideoViewContainer.removeAllViews();
////            mRtcEngine.setupRemoteVideo(new VideoCanvas(
////                    null,
////                    VideoCanvas.RENDER_MODE_HIDDEN,
////                    uid
////            ));
//        }
//    }

//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mRtcEngine != null) {
//            mRtcEngine.leaveChannel();
//            RtcEngine.destroy();
//        }
//    }
}
