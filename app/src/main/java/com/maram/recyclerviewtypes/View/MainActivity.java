package com.maram.recyclerviewtypes.View;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maram.recyclerviewtypes.Adapter.MyCustomAdapter;
import com.maram.recyclerviewtypes.R;
import com.maram.recyclerviewtypes.Utils.MyConstant;

public class MainActivity extends AppCompatActivity implements IMainCommunicator{

    /**
     *  This is the toolbar
     */
    Toolbar toolBarTop;

    /**
     * frame layout of activity
     */
    FrameLayout frameLayoutMain;

    /**
     * Toolbar Title TextView
     */
    TextView tvToolBarTitle;

    Menu menu;

    MenuItem viewListMenuItem;

    MenuItem viewGridMenuItem;

    private boolean doubleBackToExitPressedOnce = false;

    IRecyclerCommunicator iRecyclerCommunicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeActivity();
        setupToolbar();
        AttachFragment(savedInstanceState);
        setupOverFlowMenu();
    }

    private void setupOverFlowMenu() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main,menu);
        viewListMenuItem = menu.findItem(R.id.menu_list);
        viewGridMenuItem = menu.findItem(R.id.menu_grid);
        viewListMenuItem.setVisible(false);
        viewGridMenuItem.setVisible(false);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_list:
                initListView();
                break;
            case R.id.menu_grid:
                initGridView();
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    private void initListView() {
        viewGridMenuItem.setVisible(true);
        viewListMenuItem.setVisible(false);
        iRecyclerCommunicator.showListView();
    }

    private void initGridView() {
        viewGridMenuItem.setVisible(false);
        viewListMenuItem.setVisible(true);
        iRecyclerCommunicator.showGridView();
    }

    private void AttachFragment(Bundle savedInstanceState) {
        Fragment fragment;
        if(savedInstanceState == null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragment = new MenuFragment();
            fragmentTransaction.replace(frameLayoutMain.getId(),fragment);
            fragmentTransaction.commit();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolBarTop);                                            // Setting/replace toolbar as the ActionBar
        toolBarTop.setTitle("");
        tvToolBarTitle.setText(getResources().getString(R.string.menu_title));                                      // set the custom textview for the Toolbar title
        getSupportActionBar().setDisplayShowTitleEnabled(false);                    // hide the current title from the Toolbar
        //toolBarTop.setLogo(getResources().getDrawable(R.mipmap.ic_launcher));       // setting a logo in Toolbar
        //toolBarTop.setLogo(R.mipmap.ic_launcher);
        toolBarTop.setLogoDescription("LOGO");                                      // set description for the logo
        toolBarTop.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_back)); // set icon for navigation button
        toolBarTop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initializeActivity() {
        frameLayoutMain = (FrameLayout)findViewById(R.id.content_frame);
        toolBarTop = (Toolbar)findViewById(R.id.toolbar_layout);
        tvToolBarTitle = (TextView)findViewById(R.id.toolbar_title);
    }


    @Override
    public void openNextScreen(int keyValue) {
        Fragment fragmentObj;

        if(keyValue == MyConstant.NavigateScreen.FIRST_FRAGMENT_KEY){
            viewListMenuItem.setVisible(true);
            //showOverflowMenu(true);
            fragmentObj = new RecyclerFragment();
            Bundle bundle = new Bundle();
            fragmentObj.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                    .add(frameLayoutMain.getId(),fragmentObj,"INPUT_FIELD_KEY")
                    .addToBackStack("MENU_LIST_OPTION")
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void setupToolBarTitle(String toolBarName) {
        tvToolBarTitle.setText(toolBarName);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            if(fragment instanceof RecyclerFragment){
                getSupportFragmentManager().popBackStackImmediate();
                tvToolBarTitle.setText(getResources().getString(R.string.menu_title));
                viewListMenuItem.setVisible(false);
                viewGridMenuItem.setVisible(false);
            }
        }
        else{
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Click Again to Exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
}
