package kl.app.syamsul.com.kulinerlombok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kl.app.syamsul.com.kulinerlombok.R;
import kl.app.syamsul.com.kulinerlombok.model.SideMenuModel;

/**
 * Created by syamsul on 7/30/15.
 */
public class SideMenuAdapter extends BaseAdapter {

    private List<SideMenuModel> menus;
    private Context mContext;

    public SideMenuAdapter(Context context, List<SideMenuModel> menus){
        this.mContext = context;
        this.menus = menus;
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int i) {
        return menus.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        SideMenuModel menu = (SideMenuModel) getItem(i);

        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.side_menu_item, viewGroup, false);
        }

        TextView mt = (TextView) view.findViewById(R.id.menu_text);
        ImageView mi = (ImageView) view.findViewById(R.id.menu_icon);

        mt.setText(menu.getText());
        mi.setImageResource(menu.getIcon());

        return view;
    }
}
