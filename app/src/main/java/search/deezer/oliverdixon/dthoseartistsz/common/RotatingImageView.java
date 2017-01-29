package search.deezer.oliverdixon.dthoseartistsz.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import search.deezer.oliverdixon.dthoseartistsz.R;

public class RotatingImageView extends ImageView {

    public RotatingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RotatingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * @param attributeSet passed from the view initialisation.
     */
    private void init(AttributeSet attributeSet) {

        // Extract the spin speed
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.RotatingImageView, 0, 0);
        if (typedArray.hasValue(R.styleable.RotatingImageView_spinSpeed)) {
            float spinSpeed = typedArray.getFloat(R.styleable.RotatingImageView_spinSpeed, 0f);
            setSpinSpeed(spinSpeed);
        }
        typedArray.recycle();
    }

    public void setSpinSpeed(float rotationsPerSecond) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        rotateAnimation.setStartOffset(0);
        rotateAnimation.setFillAfter(true);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration((long)(rotationsPerSecond * 1000f));
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        startAnimation(rotateAnimation);
    }
}
