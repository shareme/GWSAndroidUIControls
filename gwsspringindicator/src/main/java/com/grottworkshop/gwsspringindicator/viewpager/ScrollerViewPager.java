/*
 * Copyright 2015 chenupt
 * Modifications Copyright 2015 Fred Grott(GrottWorkShop)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.grottworkshop.gwsspringindicator.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;

import java.lang.reflect.Field;


/**
 * ScrollerViewPager
 * Created by fgrott on 6/16/2015.
 */
public class ScrollerViewPager extends ViewPager {

    private static final String TAG = ScrollerViewPager.class.getSimpleName();

    private int duration = 1000;

    /**
     *
     * @param context the context
     */
    public ScrollerViewPager(Context context) {
        super(context);
    }

    /**
     *
     * @param context the context
     * @param attrs the attributeSet
     */
    public ScrollerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     *
     */
    public void fixScrollSpeed(){
        try {
            fixScrollSpeed(duration);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param duration the duration int
     */
    public void fixScrollSpeed(int duration) throws NoSuchFieldException {
        this.duration = duration;
        try {
            setScrollSpeedUsingRefection(duration);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param duration the duration int
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     */
    private void setScrollSpeedUsingRefection(int duration) throws IllegalAccessException, IllegalArgumentException, NoSuchFieldException {
        Field localField = ViewPager.class.getDeclaredField("mScroller");
        localField.setAccessible(true);
        FixedSpeedScroller scroller = new FixedSpeedScroller(getContext(), new DecelerateInterpolator(1.5F));
        scroller.setDuration(duration);
        localField.set(this, scroller);
    }

    /**
     *
     * @param ev the ev MotionEvent
     * @return the boolean false
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "onInterceptTouchEvent in IllegalArgumentException");
            return false;
        }
    }
}
