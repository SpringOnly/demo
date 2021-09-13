package amd.example.mvp.demo;

import static io.agora.mediaplayer.Constants.MediaPlayerState.PLAYER_STATE_OPEN_COMPLETED;

import io.agora.mediaplayer.IMediaPlayerObserver;

/**
 * @author Created by on LvJP 2021-8-18 , 0018
 */
public class MediaPlayerChange implements IMediaPlayerObserver {

    private OpenSuccessListener mOpenListener;
    private setVolumeListener mSetVolumeListener;


    public void setOpenSuccessListener(OpenSuccessListener listener) {
        mOpenListener = listener;
    }

    @Override
    public void onCompleted() {

    }

    public void SetVolumeListener(setVolumeListener listener) {
        mSetVolumeListener = listener;
    }

    @Override
    public void onPlayerStateChanged(io.agora.mediaplayer.Constants.MediaPlayerState mediaPlayerState,
                                     io.agora.mediaplayer.Constants.MediaPlayerError mediaPlayerError) {
        if (mediaPlayerState == PLAYER_STATE_OPEN_COMPLETED) {
            if (mOpenListener != null) {
                mOpenListener.videoOpenCompleted();
            }
            if (mSetVolumeListener != null) {
                mSetVolumeListener.setVolume();
            }
        }
    }

    @Override
    public void onPositionChanged(long l) {

    }

    @Override
    public void onPlayerEvent(io.agora.mediaplayer.Constants.MediaPlayerEvent mediaPlayerEvent) {

    }

    @Override
    public void onMetaData(io.agora.mediaplayer.Constants.MediaPlayerMetadataType mediaPlayerMetadataType, byte[] bytes) {

    }

    @Override
    public void onPlayBufferUpdated(long l) {

    }

    public interface OpenSuccessListener {
        void videoOpenCompleted();
    }

    public interface setVolumeListener {
        void setVolume();
    }

    public void cleanListener() {
        mOpenListener = null;
        mSetVolumeListener = null;
    }
}
