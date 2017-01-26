package search.deezer.oliverdixon.dthoseartistsz.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import search.deezer.oliverdixon.dthoseartistsz.R;

/**
 * Custom edit text cancel, the default Android one has many bugs that have no been resolved for years.
 */
public class EditTextCancel extends RelativeLayout {

    @BindView(R.id.edit_text) EditText editText;
    @BindView(R.id.edit_text_clear) ImageView editTextClear;

    private ReferenceObserver<String> referenceObserverNewText = new ReferenceObserver<>();

    public EditTextCancel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EditTextCancel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * @param attributeSet passed from the view initialisation.
     *
     * We need to call whenever we use the view with XML layouts so we can watch for custom attributes.
     */
    private void init(AttributeSet attributeSet) {

        // Load the custom layout resource
        inflate(getContext(), R.layout.view_edit_text_cancel, this);
        ButterKnife.bind(this);

        // Extract the hint for the edit text.
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.EditTextCancel, 0, 0);
        if (typedArray.hasValue(R.styleable.EditTextCancel_hint)) {
            String editTextHint = typedArray.getString(R.styleable.EditTextCancel_hint);
            editText.setHint(editTextHint);
        }
        typedArray.recycle();
    }

    @OnClick(R.id.edit_text_clear)
    public void clearText() {
        editText.setText("");
        editTextClear.setVisibility(GONE);
    }

    @OnTextChanged(R.id.edit_text)
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (after > 0) {
            editTextClear.setVisibility(VISIBLE);
        } else {
            editTextClear.setVisibility(GONE);
        }

        referenceObserverNewText.emit(s.toString());
    }

    public void listenForTextChanges(Action<String> newTextAction) {
        referenceObserverNewText.subscribe(newTextAction);
    }
}
