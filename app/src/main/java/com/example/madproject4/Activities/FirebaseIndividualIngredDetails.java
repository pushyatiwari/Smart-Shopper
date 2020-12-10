package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madproject4.Database.DatabaseHelper;
import com.example.madproject4.Fragments.FragmentTakePicture;
import com.example.madproject4.Model.Ingredients;
import com.example.madproject4.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FirebaseIndividualIngredDetails extends BaseActivity {
    String title, desc, added, effects, harmful, imageUrl;
    TextView title_tv, desc_tv, effects_tv;
    ImageView imageView_title, imageView_harmful;
    Ingredients tempIng;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_individual_ingred_details);
        title_tv = (TextView) findViewById(R.id.title_show_fb);
        desc_tv = (TextView) findViewById(R.id.desc_fb);
        effects_tv = (TextView) findViewById(R.id.disease_fb);
        imageView_title = (ImageView) findViewById(R.id.image_title_show_fb);
        imageView_harmful = (ImageView) findViewById(R.id.harmful_image_fb);
        dbHelper = new DatabaseHelper(this);
        title = getIntent().getStringExtra("title");
        int pos = getIntent().getIntExtra("index",-1);
        if(pos == -1)
        {
            Toast.makeText(this, "no item to be shown", Toast.LENGTH_SHORT).show();
        }
        else {
            tempIng = FragmentTakePicture.itemsArraylist.get(pos);
            desc = tempIng.getDescription() + "\n\n\nAdded in: \n" + tempIng.getAddedIn();
            effects = tempIng.getEffects();
            harmful = tempIng.getHarmful();
            imageUrl = tempIng.getImageUrl();
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
            dbHelper.insertData(title,title,desc,effects,harmful,added,imageUrl);

        }


    }
}
