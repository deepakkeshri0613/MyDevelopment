package com.deepak.mydevelopment;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class ZoomingFabBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    private static final String tag = ZoomingFabBehavior.class.getSimpleName();

    public ZoomingFabBehavior() { }

    public ZoomingFabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent,
                                   FloatingActionButton child,
                                   View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent,
                                         final FloatingActionButton child,
                                         MotionEvent ev) {
        child.animate()
                .scaleX(1.3f)
                .scaleY(1.3f)
                .setInterpolator(new LinearOutSlowInInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        child.animate().scaleX(1).scaleY(1).setDuration(500);
                    }
                })
                .setDuration(300);
        return false;
    }
}
