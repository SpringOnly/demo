package com.example.mvp.view;


import android.Manifest;
import android.os.Build;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.base.arouter.ARouterConstant;
import com.example.mvp.databinding.ActivityAgoraBinding;

import io.agora.rtc2.Constants;
import io.agora.rtc2.IAudioEffectManager;
import io.agora.rtc2.IRtcEngineEventHandler;
import io.agora.rtc2.RtcEngine;
import io.agora.rtc2.video.VideoCanvas;

@Route(path = ARouterConstant.AGORA)
public class AgoraActivity extends BaseActivity<ActivityAgoraBinding> {

    @Override
    protected ActivityAgoraBinding getViewBinding() {
        return ActivityAgoraBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        checkSelfPermission();
    }

    //检查权限
    private void checkSelfPermission() {
        PermissionUtils.permission(Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void
                    onGranted() {
                        try {
                            initEngineAndJoinChannel();
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

    private static final String appId = "babb67984093407596803d8bc444c236";
    String RoomName = "123";
    static final String TempToken = "006babb67984093407596803d8bc444c236IABGeYHT6/mS6Rr7ML7aaOxNlBt5AVNtx68OQtGwc+d2xdJjSIgAAAAAEAAabyR2N4IUYQEAAQA2ghRh";
    RtcEngine mRtcEngine;

    //初始化 RtcEngine 加入频道
    private void initEngineAndJoinChannel() throws Exception {
        mRtcEngine = RtcEngine.create(this, appId, mRtcEventHandler);
        //将频道场景设为直播。
        mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
        //视频通话功能
        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
        //设置本地视图
        setupLocalVideo(mRtcEngine);
    }

    private SurfaceView LocalView;

    private void setupLocalVideo(RtcEngine mRtcEngine) {
        //启用视频模块
        mRtcEngine.enableVideo();
        //启用音频模块
        mRtcEngine.enableAudio();

        LocalView = new SurfaceView(this);
        LocalView.setZOrderMediaOverlay(true);
        Binding.localVideoViewContainer.addView(LocalView);
        VideoCanvas videoCanvas = new VideoCanvas(LocalView, VideoCanvas.RENDER_MODE_ADAPTIVE, 0);
        mRtcEngine.setupLocalVideo(videoCanvas);
        mRtcEngine.startPreview();
        joinChannel(mRtcEngine);
    }


    //加入频道
    private void joinChannel(RtcEngine mRtcEngine) {
        mRtcEngine.joinChannel(TempToken, RoomName, "Extra Optional Data", 0);
    }


    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {


        // 注册 onJoinChannelSuccess 回调。
        // 本地用户成功加入频道时，会触发该回调。
        @Override
        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
            super.onJoinChannelSuccess(channel, uid, elapsed);
            LogUtils.e("onJoinChannelSuccess:" + uid);
        }

        @Override
        public void onConnectionStateChanged(int state, int reason) {
            super.onConnectionStateChanged(state, reason);
        }

        // 注册 onUserJoined 回调。
        // 远端用户加入频道时时，会触发该回调。
        // 可以在该回调中调用 setupRemoteVideo 方法设置远端视图。
        @Override
        public void onUserJoined(int uid, int elapsed) {
            super.onUserJoined(uid, elapsed);
            LogUtils.e("onUserJoined:" + uid);
            runOnUiThread(() -> setupRemoteVideo(uid));
        }

        private void setupRemoteVideo(int uid) {
            SurfaceView surfaceView = new SurfaceView(Binding.remoteVideoViewContainer.getContext());
            Binding.remoteVideoViewContainer.removeAllViews();
            Binding.remoteVideoViewContainer.addView(surfaceView);
            VideoCanvas videoCanvas = new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, uid);
            mRtcEngine.setupRemoteVideo(videoCanvas);
        }

        // 注册 onUserOffline 回调。
        // 远端主播离开频道或掉线时，会触发该回调。
        @Override
        public void onUserOffline(int uid, int reason) {
            super.onUserOffline(uid, reason);
            LogUtils.e("agora", "User offline, uid: " + (uid & 0xFFFFFFFFL));
            runOnUiThread(() -> onRemoteUserLeft(uid));
        }

        private void onRemoteUserLeft(int uid) {
            Binding.remoteVideoViewContainer.removeAllViews();
            mRtcEngine.setupRemoteVideo(new VideoCanvas(
                    null,
                    VideoCanvas.RENDER_MODE_HIDDEN,
                    uid
            ));
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRtcEngine != null) {
            mRtcEngine.leaveChannel();
            RtcEngine.destroy();
        }
    }

    @Override
    protected void initListener() {
        Binding.btnSwitchCamera.setOnClickListener(v -> mRtcEngine.switchCamera());
    }

    @Override
    protected void initData() {

    }
}