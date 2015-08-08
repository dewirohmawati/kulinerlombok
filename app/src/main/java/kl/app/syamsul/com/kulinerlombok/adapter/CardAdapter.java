package kl.app.syamsul.com.kulinerlombok.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
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

import java.util.List;

import kl.app.syamsul.com.kulinerlombok.fragment.DetailFragment;
import kl.app.syamsul.com.kulinerlombok.R;
import kl.app.syamsul.com.kulinerlombok.activity.DetailActivity;
import kl.app.syamsul.com.kulinerlombok.model.StoreModel;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> implements View.OnClickListener {

    /**
     * Created by syamsul on 01/08/15.
     */
    private List<StoreModel> stores;
    private int selectedItemPosition;
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

        public LinearLayout container;
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
            container = (LinearLayout) itemView.findViewById(R.id.card_adapter_container);
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

        createRating(holder,store.getRating());

        holder.storeAddress.setText(store.getAddress());
        holder.storeName.setText(store.getName());
        holder.storeImage.setImageResource(store.getP());

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

    private void createRating(ViewHolder holder, int rating){
        holder.ratingContainer.removeAllViews();

        for (int i = 0; i < 5; i++){

            ImageView img = new ImageView(mActivity);

            if(i < rating){
                img.setImageResource(R.drawable.star);
            } else {
                img.setImageResource(R.drawable.star_none);
            }
            holder.ratingContainer.addView(img);
        }
    }

    public interface RecyclerAdapterListener{
        void onRecyclerItemSelected(StoreModel data);
    }
}
