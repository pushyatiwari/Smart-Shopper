package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madproject4.R;
import com.squareup.picasso.Picasso;

public class Aboutus extends BaseActivity {
 String s = "Welcome, We're dedicated to " +
         "giving you the information of product. Our main aim is our customers health" +
         ". Use our app while shopping to SHOP SMARTLY";
 String imgUrl = "https://cdn.shopify.com/s/files/1/1444/5248/articles/almond-butter-breakfast-drizzle_1024x1024.jpg?v=1556061908";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        TextView t1 = findViewById(R.id.txtabout);
        ImageView iv = (ImageView) findViewById(R.id.au_iv);
        Picasso.with(this)// Context
                .load(imgUrl).fit().centerCrop()
                .into(iv);
        t1.setText(s);
    }
}
