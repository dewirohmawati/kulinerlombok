package kl.app.syamsul.com.kulinerlombok.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class CardFragment extends Fragment {

    private List<StoreModel> stores;
    private RecyclerView mRecycler;
    private CardAdapter mAdapter;

    public static CardFragment newInstance(String param1, String param2) {

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
                "Tanaq Maiq Masbagik Lombok Timur",
                "Jl Raya Lb. Lombok - Mataram Lombok Timur",
                "Jl. Pejanggik Sweta Mataram"
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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_card, container, false);
        mRecycler = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new CardAdapter(getActivity(), stores);

        mRecycler.setAdapter(mAdapter);

        return v;
    }

}
