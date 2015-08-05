package kl.app.syamsul.com.kulinerlombok.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import kl.app.syamsul.com.kulinerlombok.R;
import kl.app.syamsul.com.kulinerlombok.activity.DetailActivity;
import kl.app.syamsul.com.kulinerlombok.model.StoreModel;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> implements CardView.OnClickListener {

    /**
     * Created by syamsul on 01/08/15.
     */
    private Context mContext;
    private List<StoreModel> stores;

    @Override
    public void onClick(View view) {
        Intent detail = new Intent(mContext, DetailActivity.class);
        mContext.startActivity(detail);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout container;
        public LinearLayout ratingContainer;
        public TextView storeName;
        public TextView storeAddress;
        public ImageView storeImage;

        public ViewHolder(View itemView) {
            super(itemView);

            container = (LinearLayout) itemView.findViewById(R.id.card_adapter_container);
            ratingContainer = (LinearLayout) itemView.findViewById(R.id.rating_container);
            storeName = (TextView) itemView.findViewById(R.id.store_name);
            storeAddress = (TextView) itemView.findViewById(R.id.store_address);
            storeImage = (ImageView) itemView.findViewById(R.id.store_image);
        }

    }

    public CardAdapter(Context context, List<StoreModel> stores) {
        this.mContext = context;
        this.stores = stores;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.card_adpater,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        StoreModel store = stores.get(position);

        holder.ratingContainer.removeAllViews();

        for (int i = 0; i < 5; i++){

            ImageView img = new ImageView(mContext);

            if(i < store.getRating()){
                img.setImageResource(R.drawable.star);
            } else {
                img.setImageResource(R.drawable.star_none);
            }
            holder.ratingContainer.addView(img);
        }

        holder.storeAddress.setText(store.getAddress());
        holder.storeName.setText(store.getName());
        holder.storeImage.setImageResource(store.getP());

        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zoom_in));
        holder.container.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

}
