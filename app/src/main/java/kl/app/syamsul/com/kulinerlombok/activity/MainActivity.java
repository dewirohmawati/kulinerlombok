package kl.app.syamsul.com.kulinerlombok.activity;

import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kl.app.syamsul.com.kulinerlombok.R;
import kl.app.syamsul.com.kulinerlombok.adapter.SideMenuAdapter;
import kl.app.syamsul.com.kulinerlombok.model.SideMenuModel;


public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private DrawerLayout mDrawerLayout;
    private ListView mSidebarMenu;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

//        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name,R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        String[] sidebarMenuText = getResources().getStringArray(R.array.sidebar_menu);
        int[] sidebarMenuIcon = new int[]{
                R.drawable.home,
                R.drawable.pasta_and_pizza,
                R.drawable.speciality_food,
                R.drawable.traditional_food,
                R.drawable.my_favorites,
                R.drawable.discover,
                R.drawable.settings,
                R.drawable.about_us
        };

        List<SideMenuModel> sidebarMenu = new ArrayList<>();

        for(int i = 0; i < sidebarMenuText.length; i++){
            sidebarMenu.add(new SideMenuModel(sidebarMenuIcon[i],sidebarMenuText[i]));
        }

        mSidebarMenu = (ListView) findViewById(R.id.sidebar_menu_list);
        mSidebarMenu.setOnItemClickListener(this);
        mSidebarMenu.setAdapter(new SideMenuAdapter(this, sidebarMenu));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mDrawerLayout.closeDrawers();
    }
}
