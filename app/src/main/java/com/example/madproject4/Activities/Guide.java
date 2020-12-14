package com.example.madproject4.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madproject4.Model.Ingredients;
import com.example.madproject4.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Guide extends BaseActivity {
    DatabaseReference dbFood;

    String guide = "This app focuses on making customers aware about " +
            "packaged food items i.e " +
            "health effects, benefits etc." +
            "They can also search nutritional breakup of foods they are " +
            "purchasing from brands" +
            " like McDonalds, pizza hut etc, and also check the Daily Recommended Value. \n" +
            "HOW TO USE \n" +
            "Go on the menu icon on the top left of the app homescreen \n" +
            "1. Take Picture: Click at this option to take snapshot of the food's label- you will be shown" +
            "the list of ingredients. Upong clicking any ingredient name, you can view its description and health" +
            " effects.\n" +
            "2. Search Food: Alternatively, you can type the name of food eg McDonalds burger, pizza etc- you will" +
            " be shown the breakup of its nutritional value. Click the button at the bottom to check the DRA.";


    String imgUrl = "https://d1ralsognjng37.cloudfront.net/23d8215c-6d0d-4039-91a6-b666ad3d922b.jpeg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        TextView t1 = findViewById(R.id.txtGuide);
        ImageView iv = findViewById(R.id.gu_iv);
        dbFood = FirebaseDatabase.getInstance().getReference("items");
        Picasso.with(this)// Context
                .load(imgUrl).fit().centerCrop()
                .into(iv);
        t1.setText(guide);
    }

    public void adddataDb(View view) {
        Intent intent = new Intent(Guide.this,AddDataToDatabase.class );
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbFood.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot itemsn : dataSnapshot.getChildren())
                {
                 //   Ingredients ing = itemsn.getValue(Ingredients.class);
                   // Toast.makeText(Guide.this, ""+itemsn.getChildren().toString(), Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Guide.this, "on cancel", Toast.LENGTH_LONG).show();
            }


        });
    }
}
