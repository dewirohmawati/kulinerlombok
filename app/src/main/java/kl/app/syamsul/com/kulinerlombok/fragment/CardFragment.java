package kl.app.syamsul.com.kulinerlombok.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kl.app.syamsul.com.kulinerlombok.R;
import kl.app.syamsul.com.kulinerlombok.adapter.CardAdapter;
import kl.app.syamsul.com.kulinerlombok.model.StoreModel;

public class CardFragment extends Fragment  {

    private List<StoreModel> stores;
    private RecyclerView mRecycler;
    private CardAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    public static CardFragment newInstance() {

        CardFragment fragment = new CardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stores = new ArrayList<>();
        String[] st = new String[]{
                "Warung Kelor",
                "Lesehan Purnama",
                "Lesehan Burdah",
                "Ayam Taliwang Permata"
        };
        String[] al = new String[]{
                "Jl. Pejanggik Selong Lombok Timur",
                "Jl. Pejanggik Sweta Mataram",
                "Tanaq Maiq Masbagik Lombok Timur",
                "Jl Raya Lb. Lombok - Mataram Lombok Timur"
        };

        int[] rt = new int[]{3,5,4,5};
        int[] pt = new int[]{
                R.drawable.warung_kelor,
                R.drawable.lesehan_purnama,
                R.drawable.lesehan_burdah,
                R.drawable.ayam_taliwang
        };

        for(int i = 0; i < 4; i++){
            StoreModel s = new StoreModel();
            s.setAddress(al[i]);
            s.setName(st[i]);
            s.setRating(rt[i]);
            s.setP(pt[i]);
            stores.add(s);
        }

        linearLayoutManager = new LinearLayoutManager(getActivity());
        gridLayoutManager = new GridLayoutManager(getActivity(),2);

        // layout Recycler view hanya bersifat sebagai container dari
        // layout card adapter sehingga tidak perlu menggunakan layout XML
        // cukup dengan melakukan instansiasi class RecyclerView saja
        mRecycler = new RecyclerView(getActivity());
        mAdapter = new CardAdapter(getActivity(), stores);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            mRecycler.setLayoutManager(gridLayoutManager);
        } else {
            mRecycler.setLayoutManager(linearLayoutManager);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            mRecycler.setLayoutManager(gridLayoutManager);
        } else {
            mRecycler.setLayoutManager(linearLayoutManager);
        }

        mRecycler.setAdapter(mAdapter);

        return mRecycler;
    }

}
