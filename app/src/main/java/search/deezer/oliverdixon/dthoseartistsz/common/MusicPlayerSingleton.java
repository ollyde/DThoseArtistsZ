package search.deezer.oliverdixon.dthoseartistsz.common;

import android.media.MediaPlayer;
import android.support.annotation.Nullable;

import search.deezer.oliverdixon.dthoseartistsz.models.TrackModel;

public class MusicPlayerSingleton {

    private ReferenceObserver<TrackModel> trackPlayingReferenceObserver = new ReferenceObserver<>();

    private @Nullable TrackModel currentPlayingTrack = null;
    private @Nullable MediaPlayer mediaPlayer;

    private static MusicPlayerSingleton ourInstance = new MusicPlayerSingleton();
    public static MusicPlayerSingleton getInstance() {
        return ourInstance;
    }

    private MusicPlayerSingleton() {
    }

    public void playTrack(final TrackModel trackModel) {

        trackPlayingReferenceObserver.emit(trackModel);

        if (isThisTrack(trackModel)) {

            if (isThisTrackPlaying(trackModel)) {
                pause();

            } else {
                resume();
            }

        } else {
            setupMediaPlayer(trackModel);
        }
    }

    private void setupMediaPlayer(final TrackModel trackModel) {

        killPlayer();
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(trackModel.getPreview());
            mediaPlayer.prepareAsync();

            mediaPlayer.setOnPreparedListener(mp -> {
                // Start must be before emitting that the track has started.
                mediaPlayer.start();
                trackPlayingReferenceObserver.emit(trackModel);
            });

        } catch (Exception e) {
            Logger.logError("Unable to play track, preview link: " + trackModel.getPreview());
            e.printStackTrace();
            trackPlayingReferenceObserver.emit(null);
            killPlayer();
            return;
        }

        this.currentPlayingTrack = trackModel;
    }

    public void listenForTrackChange(Action<TrackModel> trackModelAction) {
        trackPlayingReferenceObserver.subscribe(trackModelAction);
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            trackPlayingReferenceObserver.emit(currentPlayingTrack);
        }
    }

    public void resume() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            trackPlayingReferenceObserver.emit(currentPlayingTrack);
        }
    }

    public void stop() {
        currentPlayingTrack = null;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            trackPlayingReferenceObserver.emit(null);
            killPlayer();
        }
    }

    public void clearTracksListening() {
        trackPlayingReferenceObserver.clear();
    }

    private void killPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
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
