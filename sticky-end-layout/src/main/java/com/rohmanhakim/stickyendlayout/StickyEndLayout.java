package com.rohmanhakim.stickyendlayout;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class StickyEndLayout extends RelativeLayout {

    private View lastView;
    private int lastViewStartMargin;
    private int lastViewEndMargin;
    private int lastViewTopMargin;
    private View firstView;
    private ViewGroup parent;
    private int maxChildWidth;

    public StickyEndLayout(Context context) {
        super(context);
    }

    public StickyEndLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        int childCount = getChildCount();

        lastView = getChildAt(childCount -1);
        lastView.measure(View.MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);

        MarginLayoutParams lastViewLayoutParams = (MarginLayoutParams) lastView.getLayoutParams();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            lastViewStartMargin = lastViewLayoutParams.getMarginStart();
            lastViewEndMargin = lastViewLayoutParams.getMarginEnd();
        } else {
            lastViewStartMargin = lastViewLayoutParams.leftMargin;
            lastViewEndMargin = lastViewLayoutParams.rightMargin;
        }

        lastViewTopMargin = lastViewLayoutParams.topMargin;

        firstView = getChildAt(0);
        firstView.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);

        parent = (ViewGroup) getParent();
        parent.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);

        maxChildWidth = 0;

        getMaxWidthFromParentChildren();
    }

    private void getMaxWidthFromParentChildren() {
        for(int i = 0 ; i < parent.getChildCount(); i++){
            int w = parent.getChildAt(i).getMeasuredWidth();
            if(maxChildWidth < w)
                maxChildWidth = w;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int lastViewTotalWidth = lastView.getMeasuredWidth() + lastViewStartMargin + lastViewEndMargin;
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int contentWidth = firstView.getMeasuredWidth() + lastViewTotalWidth;

        if(contentWidth < maxWidth ) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                    Math.max(maxChildWidth, contentWidth),
                    MeasureSpec.getMode(widthMeasureSpec));
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                    firstView.getMeasuredHeight(),
                    MeasureSpec.getMode(heightMeasureSpec));
        } else {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                    firstView.getMeasuredWidth(),
                    MeasureSpec.getMode(widthMeasureSpec));
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                    firstView.getMeasuredHeight() + lastView.getMeasuredHeight() + lastViewTopMargin,
                    MeasureSpec.getMode(heightMeasureSpec));
        }

        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        firstView.layout(0, 0, firstView.getMeasuredWidth(), firstView.getMeasuredHeight());

        lastView.layout(
                getMeasuredWidth() - lastView.getMeasuredWidth(),
                getMeasuredHeight() - lastView.getMeasuredHeight(),
                getMeasuredWidth(),
                getMeasuredHeight());

    }
}
