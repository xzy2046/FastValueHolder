/*                                                                                                                                    
 * Copyright (C) 2014 zhengyang.xu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xzy.android.fastvalueholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.method.KeyListener;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * @author zhengyang.xu
 * @version 0.1
 * @since 2014/09/28 该文件未列举全部常用接口，缺少的可以自己添加或使用getView(int
 *        viewId)得到具体View后通过View设置
 */
public class ItemBuilder {

    private final SparseArray<View> mViews;

    private final Context mContext;

    private View mConvertView;

    private View mLastView = null;

    private int mLastViewId = -1;

    private int mLayoutId = -1;

    public ItemBuilder(Context context, ViewGroup parent, int layoutId) {
        mContext = context;
        mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
        mLayoutId = layoutId;
    }

    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }

    public View getView() {
        return mConvertView;
    }

    // ImageView
    public ItemBuilder setImageResource(int viewId, int imageResId) {
        ImageView view = retrieveView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    public ItemBuilder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = retrieveView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    public ItemBuilder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = retrieveView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public ItemBuilder setColorFilter(int viewId, int color) {
        ImageView view = retrieveView(viewId);
        view.setColorFilter(color);
        return this;
    }

    public ItemBuilder setColorFilter(int viewId, ColorFilter cf) {
        ImageView view = retrieveView(viewId);
        view.setColorFilter(cf);
        return this;
    }

    public final ItemBuilder setColorFilter(int viewId, int color, PorterDuff.Mode mode) {
        ImageView view = retrieveView(viewId);
        view.setColorFilter(color, mode);
        return this;
    }

    public ItemBuilder setImageURI(int viewId, Uri uri) {
        ImageView view = retrieveView(viewId);
        view.setImageURI(uri);
        return this;
    }

    public ItemBuilder setScaleType(int viewId, ImageView.ScaleType scaleType) {
        ImageView view = retrieveView(viewId);
        view.setScaleType(scaleType);
        return this;
    }

    // TextView
    public ItemBuilder setText(int viewId, CharSequence text) {
        TextView view = retrieveView(viewId);
        view.setText(text);
        return this;
    }

    public ItemBuilder setTextColor(int viewId, int textColor) {
        TextView view = retrieveView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public ItemBuilder setTypeface(int viewId, Typeface typeface) {
        TextView view = retrieveView(viewId);
        view.setTypeface(typeface);
        // view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    public ItemBuilder setTextAppearance(int viewId, int resId) {
        TextView view = retrieveView(viewId);
        view.setTextAppearance(mContext, resId);
        return this;
    }

    public ItemBuilder setKeyListener(int viewId, KeyListener input) {
        TextView view = retrieveView(viewId);
        view.setKeyListener(input);
        return this;
    }

    public ItemBuilder setMaxLines(int viewId, int maxlines) {
        TextView view = retrieveView(viewId);
        view.setMaxLines(maxlines);
        return this;
    }

    public ItemBuilder setMinLines(int viewId, int minlines) {
        TextView view = retrieveView(viewId);
        view.setMinLines(minlines);
        return this;
    }

    public ItemBuilder setOnEditorActionListener(int viewId, TextView.OnEditorActionListener l) {
        TextView view = retrieveView(viewId);
        view.setOnEditorActionListener(l);
        return this;
    }

    public ItemBuilder setShadowLayer(int viewId, float radius, float dx, float dy, int color) {
        TextView view = retrieveView(viewId);
        view.setShadowLayer(radius, dx, dy, color);
        return this;
    }

    public ItemBuilder setSingleLine(int viewId) {
        TextView view = retrieveView(viewId);
        view.setSingleLine();
        return this;
    }

    public ItemBuilder setSingleLine(int viewId, boolean singleLine) {
        TextView view = retrieveView(viewId);
        view.setSingleLine(singleLine);
        return this;
    }

    // RatingBar
    public ItemBuilder setRating(int viewId, float rating) {
        RatingBar view = retrieveView(viewId);
        view.setRating(rating);
        return this;
    }

    // View
    public ItemBuilder setEnabled(int viewId, boolean enabled) {
        View view = retrieveView(viewId);
        view.setEnabled(enabled);
        return this;
    }

    public ItemBuilder setSelected(int viewId, boolean isSelected) {
        View view = retrieveView(viewId);
        view.setSelected(isSelected);
        return this;
    }

    public ItemBuilder setVisibility(int viewId, int visible) {
        View view = retrieveView(viewId);
        view.setVisibility(visible);
        return this;
    }

    public ItemBuilder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    // use for convertView
    public ItemBuilder setOnClickListener(View.OnClickListener listener) {
        mConvertView.setOnClickListener(listener);
        return this;
    }

    public ItemBuilder setOnKeyListener(View.OnKeyListener listener) {
        mConvertView.setOnKeyListener(listener);
        return this;
    }

    public ItemBuilder setOnFocusChangeListener(View.OnFocusChangeListener listener) {
        mConvertView.setOnFocusChangeListener(listener);
        return this;
    }

    // min api 8
    // public ItemBuilder setOnDragListener(View.OnDragListener listener) {
    // mConvertView.setOnDragListener(listener);
    // return this;
    // }

    public ItemBuilder setOnLongClickListener(View.OnLongClickListener listener) {
        mConvertView.setOnLongClickListener(listener);
        return this;
    }

    public ItemBuilder setOnTouchListener(View.OnTouchListener listener) {
        mConvertView.setOnTouchListener(listener);
        return this;
    }

    // use for convertView end

    private <T extends View> T retrieveView(int viewId) {

        if (mLastViewId == viewId && mLastView != null) {
            return (T) mLastView;
        }
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        setLastView(view, viewId);
        if (mLastView == null)
            android.util.Log.i("xzy", "!!!!!mLastView is : null layout is: " + mLayoutId);
        // return (T) view;
        return (T) mLastView;
    }

    // save LastView
    private void setLastView(final View view, final int viewId) {
        mLastViewId = viewId;
        mLastView = view;
    }

}
