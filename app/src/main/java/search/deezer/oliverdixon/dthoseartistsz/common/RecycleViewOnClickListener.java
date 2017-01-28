package search.deezer.oliverdixon.dthoseartistsz.common;

public interface RecycleViewOnClickListener {
    enum PressTime { LONG_PRESS, SHORT_PRESS };
    void viewClicked (PressTime pressTime, BaseRecycleViewHolder baseRecycleViewHolder);
}
