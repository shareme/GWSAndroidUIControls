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

package com.grottworkshop.gwsspringindicator;

/**
 * SpringView point
 * Created by fgrott on 6/16/2015.
 */
public class Point {

    private float x;
    private float y;
    private float radius;
    private int color;

    /**
     *
     * @return X
     */
    public float getX() {
        return x;
    }

    /**
     *
     * @param x the x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     *
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     *
     * @param y the y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     *
     * @return the radius
     */
    public float getRadius() {
        return radius;
    }

    /**
     *
     * @param radius the radius
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     *
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     *
     * @param color tthe color
     */
    public void setColor(int color) {
        this.color = color;
    }

}
