package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madproject4.Database.DatabaseHelper;
import com.example.madproject4.MainActivity;
import com.example.madproject4.R;
import com.squareup.picasso.Picasso;


public class ShowDetailsCapture extends BaseActivity {
    DatabaseHelper db;
    String title, desc, added, effects, harmful, imageUrl;
    TextView title_tv, desc_tv, effects_tv;
    ImageView imageView_title, imageView_harmful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_capture);


        db = new DatabaseHelper(this);
        title_tv = (TextView) findViewById(R.id.title_show);
        desc_tv = (TextView) findViewById(R.id.desc);
        effects_tv = (TextView) findViewById(R.id.disease);
        imageView_title = (ImageView) findViewById(R.id.image_title_show);
        imageView_harmful = (ImageView) findViewById(R.id.harmful_image);
        title = getIntent().getStringExtra("title");
        Cursor res = db.getData(title);

        while (res.moveToFirst()) {

            desc = res.getString(2);
            effects = res.getString(3);
            harmful = res.getString(4);
            added = res.getString(5);
            imageUrl = res.getString(6);
            break;
        }
        desc = desc + "\n\n\nAdded in: \n " + added;
        title_tv.setText(title);
        desc_tv.setText(desc);
        effects_tv.setText(effects);
        Picasso.with(this)// Context
                .load(imageUrl).fit().centerCrop()
                .into(imageView_title);
        if (harmful.toLowerCase().contains("yes")) {
            imageView_harmful.setImageResource(R.drawable.ic_error);
            imageView_harmful.setVisibility(View.VISIBLE);
        } else {
            imageView_harmful.setImageResource(R.drawable.ic_safe);
            imageView_harmful.setVisibility(View.VISIBLE);
        }
    }
}
