package com.example.madproject4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.madproject4.Activities.BaseActivity;
import com.example.madproject4.Database.DatabaseHelper;
import com.example.madproject4.Database.DatabaseHistory;
import com.example.madproject4.Fragments.AdminLogin;
import com.example.madproject4.Fragments.FragmentTakePicture;
import com.example.madproject4.Fragments.HomeFragment;
import com.example.madproject4.Fragments.SearchFoodByNutritionix;
import com.example.madproject4.Fragments.RecentSearch;
import com.example.madproject4.Model.Ingredients;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    public final static ArrayList<Ingredients> tempData = new ArrayList<>();
    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    //static ArrayList<Ingredients> itemsArraylist;
    String lastFragmentTag = null;
    boolean showingFirstFragment = true;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    DatabaseReference fb_ing;


    DatabaseHelper d ;
   // DatabaseHistory dbh;
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
        fb_ing = FirebaseDatabase.getInstance().getReference().child("ingredients");
      //  this.deleteDatabase("Ingredients2.db");
        // d = new DatabaseHelper(this);
       HomeFragment fragmentTakePicContainer = new HomeFragment();
       loadFragment(fragmentTakePicContainer);
      // dbh =  new DatabaseHistory(this);
       tempData.clear();

       //firebase get data
        fb_ing.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {

                    Ingredients ingredients = ds.getValue(Ingredients.class);
                    tempData.add(ingredients);
                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "cancelled", Toast.LENGTH_SHORT).show();
            }
        });

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
                        if(tempData.size() > 0) {
                            fragment = new FragmentTakePicture();
                            fragmentTag = "takePic";
                        }
                        else{
                            Toast.makeText(MainActivity.this, "loading...", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.nav_search_food :
                        fragment = new SearchFoodByNutritionix();
                        fragmentTag = "help";

                        break;
                    case R.id.nav_history :
                        if(tempData.size() > 0) {
                            fragment = new RecentSearch();
                            fragmentTag = "recent";
                        }
                        else{
                            Toast.makeText(MainActivity.this, "loading...", Toast.LENGTH_SHORT).show();
                        }

                        break;
//                    case R.id.nav_share :
//                        fragment = new AdminLogin();
//                        fragmentTag = "admin";
//                        break;
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
