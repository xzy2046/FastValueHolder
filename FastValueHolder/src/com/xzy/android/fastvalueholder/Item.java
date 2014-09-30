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

import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhengyang.xu
 * @version 0.1
 * @since 2014/09/28
 */
public abstract class Item<T> {

    protected T data;

    protected int mLayoutResId = -1;

    public Item(T t) {
        this(t, -1);
    }

    public Item(T t, int layoutResId) {
        data = t;
        mLayoutResId = layoutResId;
    }

    public T getData() {
        return data;
    }

    /**
     * 如果不想使用convertView可以重载getView方法，按照传统流程自行实现
     * 
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(View convertView, ViewGroup parent) {
        ItemBuilder builder = getAdapterBuilder(convertView, parent);
        convert(builder, parent, getData());
        return builder.getView();
    }

    public abstract boolean convert(ItemBuilder builder, ViewGroup parent, T data);

    public void setLayoutResource(int layoutResId) {
        mLayoutResId = layoutResId;
    }

    public int getLayoutResource() {
        return mLayoutResId;
    }

    protected ItemBuilder getAdapterBuilder(View convertView, ViewGroup parent) {
        if (convertView == null) {
            return new ItemBuilder(parent.getContext(), parent, mLayoutResId);
        }
        return (ItemBuilder) convertView.getTag();
    }
}
