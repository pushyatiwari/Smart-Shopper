package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madproject4.Fragments.FragmentTakePicture;
import com.example.madproject4.MainActivity;
import com.example.madproject4.Model.Ingredients;
import com.example.madproject4.R;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class showIndItemFromHistory extends AppCompatActivity {
    String title, desc, added, effects, harmful = "", imageUrl;
    TextView title_tv, desc_tv, effects_tv;
    ImageView imageView_title, imageView_harmful;
    Ingredients tempIng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ind_item_from_history);


    title_tv = findViewById(R.id.title_show_his);
    desc_tv = findViewById(R.id.desc_his);
    effects_tv = findViewById(R.id.disease_his);
    imageView_title = findViewById(R.id.image_title_show_his);
    imageView_harmful = findViewById(R.id.harmful_image_his);
    // dbHelper = new DatabaseHelper(this);

    title = getIntent().getStringExtra("title");
    int pos = getIntent().getIntExtra("index",-1);
        if(pos == -1)
    {
        Toast.makeText(this, "no item to be shown", Toast.LENGTH_SHORT).show();
    }
        else {
            ArrayList<Ingredients> temp = MainActivity.tempData;
            for(int i = 0; i < temp.size(); i++){
                if(temp.get(i).getTitle().equals(title))
                {
                    desc = temp.get(i).getDescription() + "\n\n\nAdded in: \n" + temp.get(i).getAddedIn();
                    effects = temp.get(i).getEffects();
                    harmful = temp.get(i).getHarmful();
                    imageUrl = temp.get(i).getImageUrl();
                }
            }

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
        //   dbHelper.insertData(title,title,desc,effects,harmful,added,imageUrl);

    }


}
}
