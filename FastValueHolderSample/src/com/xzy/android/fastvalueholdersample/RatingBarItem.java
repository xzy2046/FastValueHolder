
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
                // TODO Auto-generated method stub
                Toast.makeText(parent.getContext(), "Rating is " + rating, Toast.LENGTH_SHORT).show();
            }
            
        });
        return true;
    }

}
