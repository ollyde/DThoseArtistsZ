package search.deezer.oliverdixon.dthoseartistsz.common;

import android.view.View;

public interface RecycleViewOnClickListener {
    enum PressTime { LONG_PRESS, SHORT_PRESS };
    void viewClicked (PressTime pressTime, View view);
}
