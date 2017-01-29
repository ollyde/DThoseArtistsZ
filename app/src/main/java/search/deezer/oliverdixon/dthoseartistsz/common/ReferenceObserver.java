package search.deezer.oliverdixon.dthoseartistsz.common;

import java.util.ArrayList;
import java.util.Iterator;

public class ReferenceObserver<T> {
    private ArrayList<Action<T>> actions = new ArrayList<>();

    public void subscribe(Action<T> action) {
        this.actions.add(action);
    }

    public void emit(T value) {
        Iterator<Action<T>> actionIterator = this.actions.iterator();
        while (actionIterator.hasNext()) {
            Action<T> actionValue = actionIterator.next();
            actionValue.invoke(value);
        }
    }

    public void clear() {
        actions.clear();
    }
}
