package kl.app.syamsul.com.kulinerlombok.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

import kl.app.syamsul.com.kulinerlombok.R;
import kl.app.syamsul.com.kulinerlombok.model.StoreModel;

/**
 * Created by syamsul on 09/08/15.
 */
public class GalleryFragment extends Fragment implements ViewPager.OnPageChangeListener, Animation.AnimationListener {

    public static final String GALLERY_FRAGMENT_STORE_PHOTO = "store_photo";

    private Animation anim;
    private boolean animationSsStarted = false;
    private HashMap<String,String> photo;
    private ImageView galleryPhoto;
    private TextView photoDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if(args != null){
            photo = (HashMap) args.getSerializable(GALLERY_FRAGMENT_STORE_PHOTO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_gallery,container,false);

        galleryPhoto = (ImageView) v.findViewById(R.id.gallery_photo);
        photoDescription = (TextView) v.findViewById(R.id.gallery_description);

        Picasso.with(getActivity()).load(Integer.parseInt(photo.get(StoreModel.KEY_PHOTO_URL)))
                .resize(1080,800)
                .into(galleryPhoto);
        photoDescription.setText(photo.get(StoreModel.KEY_PHOTO_DESCRIPTION));


        return v;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(positionOffset == 0){
            anim = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
            anim.setAnimationListener(this);
            photoDescription.setAnimation(anim);
        } else {
            photoDescription.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onAnimationStart(Animation animation) {
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                photoDescription.setVisibility(View.VISIBLE);
            }
        },500);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
