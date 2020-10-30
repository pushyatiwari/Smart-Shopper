package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.madproject4.R;

public class Guide extends BaseActivity {
String guide = "Take picture of ingredients, crop it to focus on ingredients, and press ok and get the " +
        "details of ingredients used in the product";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        TextView t1 = findViewById(R.id.txtGuide);
        t1.setText(guide);
    }

    public void adddataDb(View view) {
        Intent intent = new Intent(Guide.this,AddDataToDatabase.class );
        startActivity(intent);
    }
}
