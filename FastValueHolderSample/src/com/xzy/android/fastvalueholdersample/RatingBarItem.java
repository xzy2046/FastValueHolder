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
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

import com.xzy.android.fastvalueholder.Item;
import com.xzy.android.fastvalueholder.ItemBuilder;

public class RatingBarItem extends Item<Float> {

    public RatingBarItem(Float t, int layoutResId) {
        super(t, layoutResId);
    }

    @Override
    public boolean convert(ItemBuilder builder, final ViewGroup parent, Float data) {
        builder.setRating(R.id.rating_bar, data).setStepSize(R.id.rating_bar, 0.5f);
        builder.setOnRatingBarChangeListener(R.id.rating_bar, new OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(parent.getContext(), "Rating is " + rating, Toast.LENGTH_SHORT).show();
            }
            
        });
        return true;
    }

}
