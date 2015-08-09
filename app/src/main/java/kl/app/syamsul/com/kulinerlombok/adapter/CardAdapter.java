package kl.app.syamsul.com.kulinerlombok.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import kl.app.syamsul.com.kulinerlombok.R;
import kl.app.syamsul.com.kulinerlombok.model.StoreModel;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> implements View.OnClickListener {

    /**
     * Created by syamsul on 01/08/15.
     */
    private List<StoreModel> stores;
    private Activity mActivity;
    private RecyclerAdapterListener listener;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_item_button :
                Toast.makeText(mActivity,"clicked",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView container;
        public LinearLayout ratingContainer;
        public TextView storeName;
        public TextView storeAddress;
        public ImageView storeImage;
        public FrameLayout imageFrame;
        public ImageButton itemButton;

        public ViewHolder(View itemView) {
            super(itemView);

            itemButton = (ImageButton) itemView.findViewById(R.id.card_item_button);
            imageFrame = (FrameLayout) itemView.findViewById(R.id.card_view_image_frame);
            container = (CardView) itemView.findViewById(R.id.card_view);
            ratingContainer = (LinearLayout) itemView.findViewById(R.id.rating_container);
            storeName = (TextView) itemView.findViewById(R.id.store_name);
            storeAddress = (TextView) itemView.findViewById(R.id.store_address);
            storeImage = (ImageView) itemView.findViewById(R.id.store_image);
        }

    }

    public CardAdapter(Activity activity, List<StoreModel> stores) {
        this.mActivity = activity;
        this.stores = stores;

        try{
            listener = (RecyclerAdapterListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.getClass() + " Must implement RecyclerAdapterListener");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mActivity).inflate(R.layout.card_adpater,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final StoreModel store = stores.get(position);

        int overallRating = store.getRating().get(StoreModel.KEY_RATING_OVERALL);
        createRating(holder, overallRating);

        holder.storeAddress.setText(store.getAddress());
        holder.storeName.setText(store.getName());

        Map<String,String> photo = store.getPhotos().get(0);

        Picasso.with(mActivity)
                .load(Integer.parseInt(photo.get(StoreModel.KEY_PHOTO_URL)))
                .resize(300, 250)
                .into(holder.storeImage);

        holder.container.setAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.zoom_in));
        holder.imageFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecyclerItemSelected(store);
            }
        });
        holder.itemButton.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    private void createRating(ViewHolder holder, int overallRating){

        holder.ratingContainer.removeAllViews();
        int layout;

        switch (overallRating){
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

        View rating = LayoutInflater.from(mActivity).inflate(layout,null);

        holder.ratingContainer.addView(rating);
    }

    public interface RecyclerAdapterListener{
        void onRecyclerItemSelected(StoreModel data);
        void onRecyclerItemMenuSelected(StoreModel data);
    }
}
