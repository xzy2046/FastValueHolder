
package com.xzy.android.fastvalueholdersample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
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

import com.xzy.android.fastvalueholder.ItemAdapter;

/**
 * @author zhengyang.xu
 * @version 0.1
 * @since 2014/09/28
 */
public class MainActivity extends ActionBarActivity {

    private ListView mListView;
    private ItemAdapter mItemAdapter;

    private int[] mDrawableId = {
            R.drawable.animal_0, R.drawable.animal_1,
            R.drawable.animal_2, R.drawable.animal_3, R.drawable.animal_4,
            R.drawable.animal_5, R.drawable.animal_6, R.drawable.animal_7
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) this.findViewById(R.id.listview);
        mItemAdapter = new ItemAdapter(this);
        initItems(this);
        mListView.setAdapter(mItemAdapter);
    }

    private void initItems(Context context) {
        for (int i = 0; i < mDrawableId.length; i++) {
            mItemAdapter.addItem(new PicItem(mDrawableId[i], R.layout.pic_items));
        }
        for (int i = 0; i < 10; i++) {
            TextItemData itemData = new TextItemData();
            itemData.setTitle(String.valueOf(i+100));
            itemData.setComment(String.valueOf(i+1000000));
            mItemAdapter.addItem(new TextItem(itemData));
        }
        for (int i = 0; i < 10; i++) {
            mItemAdapter.addItem(new RatingBarItem(8f, R.layout.ratingbar_item));
        }
        for (int i = 0; i < mDrawableId.length; i++) {
            mItemAdapter.addItem(new PicItem(mDrawableId[i], R.layout.pic_items));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
