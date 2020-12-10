package com.example.madproject4.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madproject4.Model.Ingredients;
import com.example.madproject4.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Guide extends BaseActivity {
    DatabaseReference dbFood;

    String guide = "Take picture of ingredients, crop it to focus on Ingredients, and press ok and get the " +
        "details of Ingredients used in the product";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        TextView t1 = findViewById(R.id.txtGuide);
        dbFood = FirebaseDatabase.getInstance().getReference("items");

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
