package search.deezer.oliverdixon.dthoseartistsz.common;

import android.util.Log;

public class Logger {

    /**
     * There would be multiple log names depending on certain senarios.
     */
    private static final String APP_LOG_NAME = "DThoseArtistsZ";

    public static void logError(String error) {
        Log.e(APP_LOG_NAME, error);
    }

    public static void logWarning(String warning) {
        Log.w(APP_LOG_NAME, warning);
    }

    public static void logInfo(String info) {
        Log.i(APP_LOG_NAME, info);
    }
}
