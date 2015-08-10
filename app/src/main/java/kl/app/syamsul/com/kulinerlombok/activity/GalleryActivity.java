package kl.app.syamsul.com.kulinerlombok.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private ViewPager.OnPageChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery);

        Intent in = getIntent();
        if(in != null){
            store = (StoreModel) in.getSerializableExtra(GALLERY_ACTIVITY_DATA);
        }

        initPager();

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        try{
            listener = (ViewPager.OnPageChangeListener) fragment;
            if(mPager != null) {
                mPager.addOnPageChangeListener(listener);
            }
        } catch (ClassCastException e){

        }
    }

    public class GalleryAdapter extends FragmentPagerAdapter {

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

    private void initPager(){
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mAdapter = new GalleryAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.setPageTransformer(true, new Animator());
    }

    private class Animator implements ViewPager.PageTransformer{
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void transformPage(View view, float position) {

            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) {
                view.setAlpha(0);
            } else if (position <= 1) {
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else {
                view.setAlpha(0);
            }
        }
    }

}
