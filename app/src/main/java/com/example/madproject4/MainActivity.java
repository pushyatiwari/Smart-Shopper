package com.example.madproject4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.madproject4.Activities.Aboutus;
import com.example.madproject4.Activities.BaseActivity;
import com.example.madproject4.Activities.ContactUs;
import com.example.madproject4.Activities.Guide;
import com.example.madproject4.Activities.ShowDetailsCapture;
import com.example.madproject4.Database.DatabaseHelper;
import com.example.madproject4.Fragments.FragmentTakePicContainer;
import com.example.madproject4.Fragments.FragmentTakePicture;
import com.example.madproject4.Fragments.HomeFragment;
import com.example.madproject4.Fragments.SafestProducts;
import com.example.madproject4.Fragments.SendEmail;
import com.example.madproject4.Fragments.ShareApp;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends BaseActivity {


    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    String lastFragmentTag = null;
    boolean showingFirstFragment = true;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    DatabaseHelper d ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
      //  this.deleteDatabase("Ingredients2.db");
        // d = new DatabaseHelper(this);
       HomeFragment fragmentTakePicContainer = new HomeFragment();
       loadFragment(fragmentTakePicContainer);



        //navView inflate
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                String fragmentTag = null;


                switch (item.getItemId())
                {

                    case R.id.nav_home :
                    MainActivity m = new MainActivity();
                    Intent i = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                    break;
                    case R.id.nav_takePicture :
                        fragment = new FragmentTakePicture();
                        fragmentTag = "takePic";

                        break;
                    case R.id.nav_helpful :
                        fragment = new SafestProducts();
                        fragmentTag = "help";

                        break;
                    case R.id.nav_send :
                        fragment = new SendEmail();
                        fragmentTag = "send";

                        break;
                    case R.id.nav_share :
                        fragment = new ShareApp();
                        fragmentTag = "share";
                        break;
                }
                if(fragment!=null) {
                    addNewFragment(fragment, fragmentTag);
                }
                drawer.closeDrawer(GravityCompat.START);

                return true;

            }
        });

    }




    public Fragment loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
        return fragment;
    }



    public void addNewFragment(Fragment fragment, String fragmentTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (lastFragmentTag != null) {
            Fragment currentFragment = fragmentManager.findFragmentByTag(lastFragmentTag);
            transaction.remove(currentFragment);
        } else {
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
            transaction.hide(currentFragment);
        }

        transaction.add(R.id.fragment_container, fragment, fragmentTag);
        transaction.commit();

        lastFragmentTag = fragmentTag;
        showingFirstFragment = false;
    }


}
