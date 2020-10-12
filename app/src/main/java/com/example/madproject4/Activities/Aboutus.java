package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.madproject4.R;

public class Aboutus extends AppCompatActivity {
 String s = "Welcome, your number one source for all" +
         " food . We're dedicated to " +
         "giving you the very best of product, with a focus on [three characteristics," +
         " ie: dependability, customer service and uniqueness.]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        TextView t1 = findViewById(R.id.txtabout);
        t1.setText(s);
    }
}
