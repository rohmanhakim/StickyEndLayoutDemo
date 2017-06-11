package com.rohmanhakim.stickyendlayoutdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class MaxWidthLinearLayout extends LinearLayout {
    private int mBoundedWidth;

    public MaxWidthLinearLayout(final Context context) {
        super(context);
    }

    public MaxWidthLinearLayout(final Context context, final AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray attr = getContext().obtainStyledAttributes(attributeSet, R.styleable.MaxWidthLinearLayout);
        int maxWidth = attr.getDimensionPixelSize(R.styleable.MaxWidthLinearLayout_maxWidth, mBoundedWidth);
        if (maxWidth > 0) {
            mBoundedWidth = maxWidth;
        }
        attr.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, final int heightMeasureSpec) {
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (mBoundedWidth < measuredWidth && mBoundedWidth > 0) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(mBoundedWidth, MeasureSpec.getMode(widthMeasureSpec));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
