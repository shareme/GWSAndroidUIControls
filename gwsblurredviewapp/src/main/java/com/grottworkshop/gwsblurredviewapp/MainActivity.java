/*
 * Copyright (c) 2015 500px Inc.
 * Modifications Copyright 2015 Fred Grott(GrottWorkShop)
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package com.grottworkshop.gwsblurredviewapp;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.grottworkshop.gwsblurredview.view.BlurringView;

import java.util.Random;

/**
 * Updated this to use the AppCompatActivity
 * Created by fgrott on 6/21/2015.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBlurringView = (BlurringView) findViewById(R.id.blurring_view);
        View blurredView = findViewById(R.id.blurred_view);

        // Give the blurring view a reference to the blurred view.
        mBlurringView.setBlurredView(blurredView);

        mImageViews[0] = (ImageView) findViewById(R.id.image0);
        mImageViews[1] = (ImageView) findViewById(R.id.image1);
        mImageViews[2] = (ImageView) findViewById(R.id.image2);
        mImageViews[3] = (ImageView) findViewById(R.id.image3);
        mImageViews[4] = (ImageView) findViewById(R.id.image4);
        mImageViews[5] = (ImageView) findViewById(R.id.image5);
        mImageViews[6] = (ImageView) findViewById(R.id.image6);
        mImageViews[7] = (ImageView) findViewById(R.id.image7);
        mImageViews[8] = (ImageView) findViewById(R.id.image8);
    }

    public void shuffle(View view) {

        // Randomly pick a different start in the array of available images.
        int newStartIndex;
        do {
            newStartIndex = mImageIds[mRandom.nextInt(mImageIds.length)];
        } while (newStartIndex == mStartIndex);
        mStartIndex = newStartIndex;

        // Update the images for the image views contained in the blurred view.
        for (int i = 0; i < mImageViews.length; i++) {
            int drawableId = mImageIds[(mStartIndex + i) % mImageIds.length];
            mImageViews[i].setImageDrawable(getResources().getDrawable(drawableId));
        }

        // Invalidates the blurring view when the content of the blurred view changes.
        mBlurringView.invalidate();
    }

    private ValueAnimator.AnimatorUpdateListener listener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mBlurringView.invalidate();
        }
    };

    public void shift(View view) {
        if (!mShifted) {
            for (ImageView imageView : mImageViews) {
                ObjectAnimator tx = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, (mRandom.nextFloat() - 0.5f) * 500);
                tx.addUpdateListener(listener);
                ObjectAnimator ty = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, (mRandom.nextFloat() - 0.5f) * 500);
                ty.addUpdateListener(listener);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(tx, ty);
                set.setDuration(3000);
                set.setInterpolator(new OvershootInterpolator());
                set.addListener(new AnimationEndListener(imageView));
                set.start();
            }
            mShifted = true;
        } else {
            for (ImageView imageView : mImageViews) {
                ObjectAnimator tx = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, 0);
                tx.addUpdateListener(listener);
                ObjectAnimator ty = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, 0);
                ty.addUpdateListener(listener);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(tx, ty);
                set.setDuration(3000);
                set.setInterpolator(new OvershootInterpolator());
                set.addListener(new AnimationEndListener(imageView));
                set.start();
            }
            mShifted = false;
        }
    }

    private BlurringView mBlurringView;

    private int[] mImageIds = {
            R.drawable.p0, R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4,
            R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9
    };

    private ImageView[] mImageViews = new ImageView[9];
    private int mStartIndex;

    private Random mRandom = new Random();

    private boolean mShifted;

    private static class AnimationEndListener implements Animator.AnimatorListener {

        View mView;

        public AnimationEndListener(View v) {
            mView = v;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            mView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mView.setLayerType(View.LAYER_TYPE_NONE, null);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            mView.setLayerType(View.LAYER_TYPE_NONE, null);
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

}
