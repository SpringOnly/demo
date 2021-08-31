package com.example.mvp.demo;

import static io.agora.mediaplayer.Constants.PLAYER_RENDER_MODE_FIT;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;


import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.util.CommonLog;

import io.agora.mediaplayer.IMediaPlayer;
import io.agora.rtc2.IAgoraEventHandler;
import io.agora.rtc2.IRtcEngineEventHandler;
import io.agora.rtc2.RtcEngine;
import io.agora.rtc2.RtcEngineConfig;
import io.agora.rtc2.UserInfo;

/**
 * @author Created by on LvJP 2021-8-18 , 0018
 */
public class AgoraPlayer extends FrameLayout implements LifecycleObserver {

    public IMediaPlayer agoraMediaPlayerKit;
    public MediaPlayerChange mMediaPlayerChange;

    public AgoraPlayer(Context context) {
        this(context, null);
    }

    public AgoraPlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AgoraPlayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        RtcEngineConfig config = new RtcEngineConfig();
        config.mContext = context;
        config.mAppId = "316e9a6a2365400c985c792a561ff6d4";
        config.mEventHandler = new FuaEventHandler();

        RtcEngine rtcEngine = null;
        try {
            rtcEngine = RtcEngine.create(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rtcEngine == null) {
            return;
        }
        agoraMediaPlayerKit = rtcEngine.createMediaPlayer();
        mMediaPlayerChange = new MediaPlayerChange();
        agoraMediaPlayerKit.registerPlayerObserver(mMediaPlayerChange);

        SurfaceView surfaceView = new SurfaceView(context);
        surfaceView.setZOrderMediaOverlay(true);

        if (getChildCount() > 0) {
            removeAllViews();
        }
        addView(surfaceView);
        agoraMediaPlayerKit.setView(surfaceView);
        agoraMediaPlayerKit.setRenderMode(PLAYER_RENDER_MODE_FIT);
    }

    /**
     * 设置视频播放url 支持网络路径和本地路径
     *
     * @param playUrl 视频地址的路径
     */
    public void setDataSource(String playUrl) {
        if (agoraMediaPlayerKit != null) {
            agoraMediaPlayerKit.open(playUrl, 0);
        }
    }

    /**
     * 开始播放
     */
    public void start() {
        mMediaPlayerChange.setOpenSuccessListener(() -> {
            if (agoraMediaPlayerKit != null) {
                agoraMediaPlayerKit.play();
            }
        });
    }

    /**
     * 绑定生命周期管理
     */
    public void bindLifecycle(BaseActivity activity) {
        activity.getLifecycle().addObserver(this);
    }

    /**
     * 设置音量
     *
     * @param volume 音量值
     */
    public void setVolume(int volume) {
        mMediaPlayerChange.SetVolumeListener(() -> {
            if (agoraMediaPlayerKit != null) {
                agoraMediaPlayerKit.adjustPlayoutVolume(volume);
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
        CommonLog.e("ON_RESUME");
        if (agoraMediaPlayerKit != null) {
            agoraMediaPlayerKit.play();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
        CommonLog.e("ON_PAUSE");
        if (agoraMediaPlayerKit != null) {
            agoraMediaPlayerKit.pause();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        CommonLog.e("ON_DESTROY");
        agoraMediaPlayerKit = null;
        mMediaPlayerChange.cleanListener();
    }

    public class FuaEventHandler implements IAgoraEventHandler {
        @Override
        public void onWarning(int i) {

        }

        @Override
        public void onError(int i) {

        }

        @Override
        public void onApiCallExecuted(int i, String s, String s1) {

        }

        @Override
        public void onCameraReady() {

        }

        @Override
        public void onCameraFocusAreaChanged(Rect rect) {

        }

        @Override
        public void onVideoStopped() {

        }

        @Override
        public void onLeaveChannel(IRtcEngineEventHandler.RtcStats rtcStats) {

        }

        @Override
        public void onRtcStats(IRtcEngineEventHandler.RtcStats rtcStats) {

        }

        @Override
        public void onAudioVolumeIndication(IRtcEngineEventHandler.AudioVolumeInfo[] audioVolumeInfos, int i) {

        }

        @Override
        public void onLastmileQuality(int i) {

        }

        @Override
        public void onLastmileProbeResult(IRtcEngineEventHandler.LastmileProbeResult lastmileProbeResult) {

        }

        @Override
        public void onLocalVideoStat(int i, int i1) {

        }

        @Override
        public void onRemoteVideoStats(IRtcEngineEventHandler.RemoteVideoStats remoteVideoStats) {

        }

        @Override
        public void onRemoteAudioStats(IRtcEngineEventHandler.RemoteAudioStats remoteAudioStats) {

        }

        @Override
        public void onLocalVideoStats(IRtcEngineEventHandler.LocalVideoStats localVideoStats) {

        }

        @Override
        public void onLocalAudioStats(IRtcEngineEventHandler.LocalAudioStats localAudioStats) {

        }

        @Override
        public void onFirstLocalVideoFrame(int i, int i1, int i2) {

        }

        @Override
        public void onConnectionLost() {

        }

        @Override
        public void onConnectionInterrupted() {

        }

        @Override
        public void onConnectionStateChanged(int i, int i1) {

        }

        @Override
        public void onNetworkTypeChanged(int i) {

        }

        @Override
        public void onConnectionBanned() {

        }

        @Override
        public void onRefreshRecordingServiceStatus(int i) {

        }

        @Override
        public void onMediaEngineLoadSuccess() {

        }

        @Override
        public void onMediaEngineStartCallSuccess() {

        }

        @Override
        public void onAudioMixingFinished() {

        }

        @Override
        public void onRequestToken() {

        }

        @Override
        public void onAudioRouteChanged(int i) {

        }

        @Override
        public void onAudioMixingStateChanged(int i, int i1) {

        }

        @Override
        public void onFirstLocalAudioFramePublished(int i) {

        }

        @Override
        public void onAudioEffectFinished(int i) {

        }

        @Override
        public void onClientRoleChanged(int i, int i1) {

        }

        @Override
        public void onRtmpStreamingStateChanged(String s, IRtcEngineEventHandler.RTMP_STREAM_PUBLISH_STATE rtmp_stream_publish_state, IRtcEngineEventHandler.RTMP_STREAM_PUBLISH_ERROR rtmp_stream_publish_error) {

        }

        @Override
        public void onStreamPublished(String s, int i) {

        }

        @Override
        public void onStreamUnpublished(String s) {

        }

        @Override
        public void onTranscodingUpdated() {

        }

        @Override
        public void onTokenPrivilegeWillExpire(String s) {

        }

        @Override
        public void onLocalPublishFallbackToAudioOnly(boolean b) {

        }

        @Override
        public void onChannelMediaRelayStateChanged(int i, int i1) {

        }

        @Override
        public void onChannelMediaRelayEvent(int i) {

        }

        @Override
        public void onIntraRequestReceived() {

        }

        @Override
        public void onUplinkNetworkInfoUpdated(IRtcEngineEventHandler.UplinkNetworkInfo uplinkNetworkInfo) {

        }

        @Override
        public void onDownlinkNetworkInfoUpdated(IRtcEngineEventHandler.DownlinkNetworkInfo downlinkNetworkInfo) {

        }

        @Override
        public void onEncryptionError(IRtcEngineEventHandler.ENCRYPTION_ERROR_TYPE encryption_error_type) {

        }

        @Override
        public void onPermissionError(IRtcEngineEventHandler.PERMISSION permission) {

        }

        @Override
        public void onLocalUserRegistered(int i, String s) {

        }

        @Override
        public void onUserInfoUpdated(int i, UserInfo userInfo) {

        }

        @Override
        public void onFirstLocalVideoFramePublished(int i) {

        }

        @Override
        public void onAudioSubscribeStateChanged(String s, int i, IRtcEngineEventHandler.STREAM_SUBSCRIBE_STATE stream_subscribe_state, IRtcEngineEventHandler.STREAM_SUBSCRIBE_STATE stream_subscribe_state1, int i1) {

        }

        @Override
        public void onVideoSubscribeStateChanged(String s, int i, IRtcEngineEventHandler.STREAM_SUBSCRIBE_STATE stream_subscribe_state, IRtcEngineEventHandler.STREAM_SUBSCRIBE_STATE stream_subscribe_state1, int i1) {

        }

        @Override
        public void onAudioPublishStateChanged(String s, IRtcEngineEventHandler.STREAM_PUBLISH_STATE stream_publish_state, IRtcEngineEventHandler.STREAM_PUBLISH_STATE stream_publish_state1, int i) {

        }

        @Override
        public void onVideoPublishStateChanged(String s, IRtcEngineEventHandler.STREAM_PUBLISH_STATE stream_publish_state, IRtcEngineEventHandler.STREAM_PUBLISH_STATE stream_publish_state1, int i) {

        }
    }

}
