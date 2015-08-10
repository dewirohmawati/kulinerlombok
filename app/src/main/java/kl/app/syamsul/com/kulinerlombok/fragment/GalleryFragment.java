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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kl.app.syamsul.com.kulinerlombok.R;
import kl.app.syamsul.com.kulinerlombok.model.StoreModel;

/**
 * Created by syamsul on 09/08/15.
 */
public class GalleryFragment extends Fragment {

    public static final String GALLERY_FRAGMENT_STORE_PHOTO = "store_photo";

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

        View v = inflater.inflate(R.layout.activity_gallery,container,false);

        galleryPhoto = (ImageView) v.findViewById(R.id.gallery_photo);
        photoDescription = (TextView) v.findViewById(R.id.gallery_description);

        Picasso.with(getActivity()).load(Integer.parseInt(photo.get(StoreModel.KEY_PHOTO_URL)))
                .resize(800,1080)
                .into(galleryPhoto);
        photoDescription.setText(photo.get(StoreModel.KEY_PHOTO_DESCRIPTION));

        return v;
    }

}
