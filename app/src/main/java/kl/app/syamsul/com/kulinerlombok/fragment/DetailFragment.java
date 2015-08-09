package kl.app.syamsul.com.kulinerlombok.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.manuelpeinado.fadingactionbar.view.ObservableScrollView;
import com.manuelpeinado.fadingactionbar.view.OnScrollChangedCallback;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import kl.app.syamsul.com.kulinerlombok.R;
import kl.app.syamsul.com.kulinerlombok.activity.DetailActivity;
import kl.app.syamsul.com.kulinerlombok.model.StoreModel;


public class DetailFragment extends Fragment implements OnScrollChangedCallback {

    public static final String ARG_STORE_DATA = "store";

    private StoreModel store;
    private View mHeader;
    private Toolbar mToolbar;
    private int mLastDampedScroll;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 SotreModel data
     * @return A new instance of fragment DetailFragment.
     */
    public static DetailFragment newInstance(StoreModel param1) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_STORE_DATA, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        mToolbar = ((DetailActivity)getActivity()).mToolbar;

        Holder holder = new Holder(v);
        Map<String,String> photo = store.getPhotos().get(0);

        Picasso.with(getActivity())
                .load(Integer.parseInt(photo.get(StoreModel.KEY_PHOTO_URL)))
                .resize(800, 500)
                .into(holder.storeImage);

        holder.storeName.setText(store.getName());
        holder.storeAddress.setText(store.getAddress());

        Map<String,Integer> rating = store.getRating();

        holder.ratingContainer.get(StoreModel.KEY_RATING_COMFORT).addView(generateRatingView(rating.get(StoreModel.KEY_RATING_COMFORT)));
        holder.ratingContainer.get(StoreModel.KEY_RATING_CLEANNESS).addView(generateRatingView(rating.get(StoreModel.KEY_RATING_CLEANNESS)));
        holder.ratingContainer.get(StoreModel.KEY_RATING_SERVICE).addView(generateRatingView(rating.get(StoreModel.KEY_RATING_SERVICE)));
        holder.ratingContainer.get(StoreModel.KEY_RATING_TASTE).addView(generateRatingView(rating.get(StoreModel.KEY_RATING_TASTE)));

        mHeader = holder.storeImage;

        ObservableScrollView o = (ObservableScrollView) v.findViewById(R.id.observable);
        o.setOnScrollChangedCallback(this);
        onScroll(-1,0);

        v.setTag(holder);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getArguments() != null) {
            store = (StoreModel) getArguments().getSerializable(ARG_STORE_DATA);
        }
    }

    private class Holder {

        public ImageView storeImage;
        public TextView storeName;
        public TextView storeAddress;
        public TextView storeDescription;
        public Map<String, FrameLayout> ratingContainer;

        public Holder(View v){
            storeImage = (ImageView) v.findViewById(R.id.store_image);
            storeName = (TextView) v.findViewById(R.id.store_name);
            storeAddress = (TextView) v.findViewById(R.id.store_address);
            storeDescription = (TextView) v.findViewById(R.id.store_description);

            ratingContainer = new HashMap<>();

            ratingContainer.put(StoreModel.KEY_RATING_CLEANNESS,(FrameLayout) v.findViewById(R.id.rating_cleanness_container));
            ratingContainer.put(StoreModel.KEY_RATING_COMFORT,(FrameLayout) v.findViewById(R.id.rating_comfort_container));
            ratingContainer.put(StoreModel.KEY_RATING_TASTE,(FrameLayout) v.findViewById(R.id.rating_taste_container));
            ratingContainer.put(StoreModel.KEY_RATING_SERVICE,(FrameLayout) v.findViewById(R.id.rating_service_container));
        }
    }

    @Override
    public void onScroll(int l, int scrollPosition) {

        int headerHeight = mHeader.getHeight() - mToolbar.getHeight();
        float ratio = 0;
        if (scrollPosition > 0 && headerHeight > 0)
            ratio = (float) Math.min(Math.max(scrollPosition, 0), headerHeight) / headerHeight;

        updateActionBarTransparency(ratio);
        generateParallaxEffect(scrollPosition);
    }

    private void generateParallaxEffect(int scrollPosition){
        float damping = 0.5f;

        int dampedScroll = (int) (scrollPosition * damping);
        int offset = mLastDampedScroll - dampedScroll;

        mHeader.offsetTopAndBottom(-offset);
        mLastDampedScroll = dampedScroll;
    }

    private void updateActionBarTransparency(float scrollRatio) {
        int newAlpha = (int) (scrollRatio * 255);
        mToolbar.getBackground().setAlpha(newAlpha);

        if(scrollRatio >= 0.8){
            getActivity().setTitle(store.getName());
        } else {
            getActivity().setTitle(null);
        }
    }

    private View generateRatingView(int rating){

        int layout;

        switch (rating){
            case 1:
                layout = R.layout.rating_one;
                break;
            case 2:
                layout = R.layout.rating_two;
                break;
            case 3:
                layout = R.layout.rating_three;
                break;
            case 4:
                layout = R.layout.rating_four;
                break;
            case 5:
                layout = R.layout.rating_five;
                break;
            default:
                layout = R.layout.rating_zero;
                break;
        }

        return LayoutInflater.from(getActivity()).inflate(layout,null);
    }

}
