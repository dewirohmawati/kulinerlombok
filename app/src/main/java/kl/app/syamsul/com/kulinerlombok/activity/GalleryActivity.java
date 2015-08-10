package kl.app.syamsul.com.kulinerlombok.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.HashMap;
import java.util.Map;

import kl.app.syamsul.com.kulinerlombok.R;
import kl.app.syamsul.com.kulinerlombok.fragment.GalleryFragment;
import kl.app.syamsul.com.kulinerlombok.model.StoreModel;

/**
 * Created by syamsul on 09/08/15.
 */
public class GalleryActivity extends FragmentActivity {

    public static final String GALLERY_ACTIVITY_DATA = "data";

    private ViewPager mPager;
    private PagerAdapter mAdapter;
    private StoreModel store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery);

        Intent in = getIntent();
        if(in != null){
            store = (StoreModel) in.getSerializableExtra(GALLERY_ACTIVITY_DATA);
        }

        mPager = (ViewPager) findViewById(R.id.view_pager);
        mAdapter = new GalleryAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);

    }

    private class GalleryAdapter extends FragmentStatePagerAdapter {

        public GalleryAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            HashMap<String,String> p = store.getPhotos().get(position);
            args.putSerializable(GalleryFragment.GALLERY_FRAGMENT_STORE_PHOTO,p);

            GalleryFragment g = new GalleryFragment();
            g.setArguments(args);
            return g;
        }

        @Override
        public int getCount() {
            return store.getPhotos().size();
        }
    }
}
