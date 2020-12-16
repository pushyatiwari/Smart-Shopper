package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.madproject4.Fragments.AdminLogin;
import com.example.madproject4.R;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        AdminLogin adminLogin = new AdminLogin();
        loadFragment(adminLogin);
    }
    public Fragment loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.admin_act, fragment);
        transaction.commit();
        return fragment;
    }
}
