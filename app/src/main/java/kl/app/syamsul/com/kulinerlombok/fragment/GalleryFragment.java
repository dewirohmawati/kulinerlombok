package kl.app.syamsul.com.kulinerlombok.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import kl.app.syamsul.com.kulinerlombok.R;
import kl.app.syamsul.com.kulinerlombok.model.StoreModel;

/**
 * Created by syamsul on 09/08/15.
 */
public class GalleryFragment extends Fragment {

    public static final String STORE_GALLERY_DATA = "store";

    private StoreModel store;
    private List<Map<String,String>> photos;
    private ViewPager mPager;
    private PagerAdapter mAdapter;

    public static GalleryFragment newInstance(StoreModel store){

        Bundle args = new Bundle();
        args.putSerializable(STORE_GALLERY_DATA, store);

        GalleryFragment g = new GalleryFragment();
        g.setArguments(args);

        return g;
    }

    @Override
    public void onAttach(Activity activity){
        Bundle args = getArguments();
        if(args != null){
            store = (StoreModel) args.getSerializable(STORE_GALLERY_DATA);
            photos = store.getPhotos();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_gallery,container,false);

        mPager = (ViewPager) v.findViewById(R.id.view_pager);
        mAdapter = new GalleryAdapter(getActivity().getSupportFragmentManager(),photos.size());
        mPager.setAdapter(mAdapter);

        return v;
    }

    private class GalleryAdapter extends FragmentStatePagerAdapter {

        public GalleryAdapter(FragmentManager fm, int photoCount) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new GalleryFragment();
        }

        @Override
        public int getCount() {
            return photos.size();
        }
    }

}
