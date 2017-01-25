package search.deezer.oliverdixon.dthoseartistsz.common;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class ReferenceObserver<T> {
    private ArrayList<WeakReference<Action<T>>> weakReferences = new ArrayList<>();

    public void subscribe(Action<T> action) {
        this.weakReferences.add(new WeakReference<>(action));
    }

    public void emit(T value) {
        Iterator<WeakReference<Action<T>>> weakReferenceIterator = this.weakReferences.iterator();
        while (weakReferenceIterator.hasNext()) {
            WeakReference<Action<T>> weakReferenceValue = weakReferenceIterator.next();

            // Remove the action if it doesn't exist.
            if (weakReferenceValue.get() == null) {
                weakReferenceIterator.remove();
            } else {
                weakReferenceValue.get().invoke(value);
            }
        }
    }
}
