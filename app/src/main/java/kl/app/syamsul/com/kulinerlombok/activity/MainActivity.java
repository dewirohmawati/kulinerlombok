package kl.app.syamsul.com.kulinerlombok.activity;

import android.graphics.Point;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import kl.app.syamsul.com.kulinerlombok.R;

public class MainActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    protected ActionBar mActionBar;

    private int screenWidth;
    private int screenHeight;
    private int layout;

    public MainActivity(int layout){
        this.layout = layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        initToolbar();

        computerScreenSize();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    protected void initToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();

        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
    }

    private void computerScreenSize(){
        Display d = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();

        d.getMetrics(metrics);

        this.screenHeight = metrics.heightPixels;
        this.screenWidth = metrics.widthPixels;
    }

    public int[] getScreenSize(){
        return new int[]{this.screenWidth,this.screenHeight};
    }
}
