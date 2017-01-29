package search.deezer.oliverdixon.dthoseartistsz.common;

import android.media.MediaPlayer;
import android.support.annotation.Nullable;

import search.deezer.oliverdixon.dthoseartistsz.models.TrackModel;

public class MusicPlayerSingleton {

    private @Nullable TrackModel currentPlayingTrack = null;
    private @Nullable MediaPlayer mediaPlayer;

    private static MusicPlayerSingleton ourInstance = new MusicPlayerSingleton();
    public static MusicPlayerSingleton getInstance() {
        return ourInstance;
    }

    private MusicPlayerSingleton() {
    }

    public void playTrack(final TrackModel trackModel) {
        if (isThisTrack(trackModel)) {
            pause();
        } else {
            setupMediaPlayer(trackModel);
        }
    }

    private void setupMediaPlayer(final TrackModel trackModel) {
        stop();
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(trackModel.getPreview());
            mediaPlayer.prepare();

        } catch (Exception e) {
            Logger.logError("Unable to play track, preview link: " + trackModel.getPreview());
            e.printStackTrace();
            return;
        }

        mediaPlayer.start();
        this.currentPlayingTrack = trackModel;
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        currentPlayingTrack = null;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }

    public boolean isThisTrack(final TrackModel trackModel) {
        return currentPlayingTrack != null && currentPlayingTrack.getId().equals(trackModel.getId());
    }

    public boolean isThisTrackPlaying(final TrackModel trackModel) {
        return isThisTrack(trackModel) && mediaPlayer != null && mediaPlayer.isPlaying();
    }
}
