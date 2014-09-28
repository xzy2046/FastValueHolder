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

package com.xzy.android.fastvalueholdersample;

import android.view.ViewGroup;

import com.xzy.android.fastvalueholder.Item;
import com.xzy.android.fastvalueholder.ItemBuilder;

/**
 * @author zhengyang.xu
 * @version 0.1
 * @since 2014/09/28
 */
public class TextItem extends Item<TextItemData> {

    public TextItem(TextItemData t) {
        super(t);
        this.setLayoutResource(R.layout.text_items);
    }

    @Override
    public boolean convert(ItemBuilder builder, ViewGroup parent, TextItemData data) {
        builder.setText(R.id.text_title, data.title).setText(R.id.text_comment, data.comment);
        return true;
    }
}
