package search.deezer.oliverdixon.dthoseartistsz.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import search.deezer.oliverdixon.dthoseartistsz.R;

/**
 * A view that displays an icon to the left of the text.
 *
 * Could be extended to display icons and side of the view.
 */
public class TextViewWithIcon extends LinearLayout {

    @BindView(R.id.text_view_with_icon_left) ImageView iconToLeftImageView;
    @BindView(R.id.text_view_with_icon_text) TextView textToRightOfIcon;

    public TextViewWithIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TextViewWithIcon(Context context, AttributeSet attrs, int defStyleAttr) {
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
        inflate(getContext(), R.layout.view_text_view_with_icon, this);
        ButterKnife.bind(this);

        // Get the custom attributes
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.TextViewWithIcon, 0, 0);

        // Extract the image for the icon
        if (typedArray.hasValue(R.styleable.TextViewWithIcon_iconLeft)) {
            final Drawable drawable = typedArray.getDrawable(R.styleable.TextViewWithIcon_iconLeft);
            iconToLeftImageView.setImageDrawable(drawable);
        } else {
            Logger.logError("Icon was not assigned to TextViewWithIcon");
        }

        // Extract the text for this custom view.
        if (typedArray.hasValue(R.styleable.TextViewWithIcon_text)) {
            final String text = typedArray.getString(R.styleable.TextViewWithIcon_text);
            textToRightOfIcon.setText(text);

        } else {
            Logger.logError("Text was not assigned to TextViewWithIcon");
        }

        typedArray.recycle();
    }
}
