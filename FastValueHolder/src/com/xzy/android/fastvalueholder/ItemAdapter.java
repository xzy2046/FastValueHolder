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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhengyang.xu
 * @version 0.1
 * @since 2014/09/28
 */
public class ItemAdapter extends BaseAdapter {

    protected List<Item> mData = new ArrayList<Item>();

    protected Context mContext;

    // ------------------------------------
    private List<ItemLayout> mItemLayouts;

    private ItemLayout mTempItemLayout = new ItemLayout();

    private boolean mHasReturnedViewTypeCount = false;// 是否已经调用getViewTypeCount

    // TODO 重构SimpleListFragment.java,添加更全面的接口
    private final int MAX_TYPE_COUNT = 10;

    private static final Boolean DEBUG = true;

    private static final String TAG = "ItemAdapter";

    // ------------------------------------

    public ItemAdapter(Context context) {
        mContext = context;
        mItemLayouts = new ArrayList<ItemLayout>();
    }

    // ------------------------START-----------------------
    // 加快查询速度,替代for each
    private static class ItemLayout implements Comparable<ItemLayout> {

        // private int resId;
        //
        // private String name;

        private Class<?> clz;

        public int compareTo(ItemLayout other) {
            // int compareNames = name.compareTo(other.name);
            // //类名相同比较用的layout是否相同，提高通用性
            // if (compareNames == 0) {
            // if (resId == other.resId) {
            // return 0;
            // } else {
            // return resId - other.resId;
            // }
            // } else {
            // return compareNames;
            // }

            if (clz.hashCode() == other.clz.hashCode()) {
                return 0;
            } else if (clz.hashCode() < other.clz.hashCode()) {
                return -1;
            } else {
                return 1;
            }

        }
    }

    private ItemLayout createItemLayout(Item<?> item, ItemLayout in) {
        ItemLayout il = in != null ? in : new ItemLayout();
        // il.name = item.getClass().getName();
        // il.resId = item.getLayoutResource();
        il.clz = item.getClass();
        return il;
    }

    private void addToItemLayouts(Item<?> item) {
        final ItemLayout il = createItemLayout(item, null);
        int insertPos = Collections.binarySearch(mItemLayouts, il);

        // 不存在就添加一个类型
        if (insertPos < 0) {
            insertPos = insertPos * -1 - 1;
            mItemLayouts.add(insertPos, il);
        }

    }

    // ------------------------END----------------------

    // 该接口暂时没用
    public void setTypeCount(int num) {
        if (mHasReturnedViewTypeCount) {
            throw new IllegalArgumentException("must call setTypeCount before setAdapter");
        }
        // mTypeCount = num;
    }

    public int indexOfItem(Item<?> item) {
        return mData.indexOf(item);
    }

    public void addItem(final Item<?> item) {
        mData.add(item);
        addToItemLayouts(item);
        notifyDataSetChanged();
    }

    public void addItem(int idx, final Item<?> item) {
        mData.add(idx, item);
        addToItemLayouts(item);
        notifyDataSetChanged();
    }

    public void addItems(final ArrayList<Item> items) {
        mData.addAll(items);
        for (Item<?> item : items) {
            addToItemLayouts(item);
        }
        notifyDataSetChanged();
    }

    public Item<?> removeItem(int idx) {
        if (idx < mData.size()) {
            Item<?> t = mData.remove(idx);
            notifyDataSetChanged();
            return t;
        } else {
            return null;
        }
    }

    public void removeItem(Class<?> clz) {
        Iterator<Item> iterator = mData.iterator();
        boolean find = false;
        while (iterator.hasNext()) {
            if (clz.isInstance(iterator.next())) {
                find = true;
                iterator.remove();
            }
        }
        if (find) {
            this.notifyDataSetChanged();
        }
    }

    public void clearItems() {
        mData.clear();
        // 不清mItemLayouts 是为了让getItemViewType始终得到正确的值
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (!mHasReturnedViewTypeCount) {
            mHasReturnedViewTypeCount = true;
        }

        final Item<?> item = this.getItem(position);
        mTempItemLayout = createItemLayout(item, mTempItemLayout);
        int viewType = Collections.binarySearch(mItemLayouts, mTempItemLayout);
        if (DEBUG) {
            Log.i(TAG, "getItemView Type is : " + viewType + " position is : " + position
                    + " item class is : " + item.getClass().getName());
        }
        if (viewType < 0) {
            // 未测试：viewType正常情况下不会查不到,如果<0,属于异常情况。
            // 异常情况view不复用。
            if (DEBUG) {
                Log.e(TAG, "ERROR : viewType < 0!!!");
            }
            return IGNORE_ITEM_VIEW_TYPE;
        } else {
            return viewType;
        }
    }

    @Override
    public int getViewTypeCount() {
        if (!mHasReturnedViewTypeCount) {
            mHasReturnedViewTypeCount = true;
        }
        // 此处会造成内存额外开销，如需避免，需要在SimpleListFragment.java执行setAdapter之前添加所有item
        return MAX_TYPE_COUNT;
        // return Math.max(1, mItemLayouts.size());
    }

    // 某一种item出现的个数，
    public int getCount(Class<?> clz) {
        int num = 0;
        for (Item<?> item : mData) {
            if (item.getClass().equals(clz)) {
                num++;
            }
        }
        return num;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Item<?> getItem(int position) {
        if (position < 0 || position >= getCount()) {
            return null;
        }
        Item<?> item = mData.get(position);
        return item;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO if Error return?
        final Item<?> item = this.getItem(position);
        if (item == null) {
            Log.e(TAG, "ERROR item is NULL");
        }
        mTempItemLayout = createItemLayout(item, mTempItemLayout);

        if (Collections.binarySearch(mItemLayouts, mTempItemLayout) < 0) {
            if (DEBUG) {
                Log.v(TAG, "ERROR getView binarySearch not found");
            }
            convertView = null;
        }

        return item.getView(convertView, parent);
    }

}
