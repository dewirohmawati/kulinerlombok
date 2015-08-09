package kl.app.syamsul.com.kulinerlombok.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import kl.app.syamsul.com.kulinerlombok.R;
import kl.app.syamsul.com.kulinerlombok.fragment.DetailFragment;
import kl.app.syamsul.com.kulinerlombok.model.StoreModel;

public class DetailActivity extends AppCompatActivity {

    public static final String BUNDLE_STORE_DATA = "store_data";

    public Toolbar mToolbar;
    public  ActionBar mActionBar;
    private StoreModel store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        store = (StoreModel) getIntent().getSerializableExtra(BUNDLE_STORE_DATA);
        setContentView(R.layout.activity_detail);

        setTitle(null);
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();

        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);

        if(savedInstanceState == null){
            Fragment f = DetailFragment.newInstance(store);
            getSupportFragmentManager().beginTransaction().add(R.id.detail_container, f).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_share){

        }
        return true;
    }
}
