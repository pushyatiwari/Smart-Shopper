package com.example.madproject4.Activities;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madproject4.MainActivity;
import com.example.madproject4.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_side, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.home_menu:
                Intent intent_home = new Intent(BaseActivity.this, MainActivity.class);
                startActivity(intent_home);
                finish();
                return true;
            case R.id.menu_guide:
                Intent i = new Intent(BaseActivity.this, Guide.class);
                startActivity(i);
                return true;
            case R.id.menu_aboutus:
                Intent i1 = new Intent(BaseActivity.this, Aboutus.class);
                startActivity(i1);
                return true;
            case R.id.menu_contact:
                Intent i2 = new Intent(BaseActivity.this, ContactUs.class);
                startActivity(i2);                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}