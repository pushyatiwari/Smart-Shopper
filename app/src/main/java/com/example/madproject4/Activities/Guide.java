package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.madproject4.R;

public class Guide extends AppCompatActivity {
String guide = "Take picture of ingredients, crop it to focus on ingredients, and press ok and get the " +
        "details of ingredients used in the product";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        TextView t1 = findViewById(R.id.txtGuide);
        t1.setText(guide);
    }
}
