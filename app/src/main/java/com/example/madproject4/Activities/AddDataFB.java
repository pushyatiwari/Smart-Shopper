package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madproject4.Model.Ingredients;
import com.example.madproject4.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDataFB extends BaseActivity {
    private EditText id, title, desc, effects, harmful, addedin, imageurl;
    private Button btnAddData;
    Button btnclear;
    DatabaseReference ingred_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_fb);
        id = findViewById(R.id.id_fb);
        title = findViewById(R.id.title_fb);
        desc = findViewById(R.id.desc_fb);
        effects = findViewById(R.id.effects_fb);
        harmful = findViewById(R.id.harmful_fb);
        addedin = findViewById(R.id.addedin_fb);
        imageurl = findViewById(R.id.imageurl_fb);
        btnAddData = findViewById(R.id.insert_dbbtn_fb);
        btnclear = findViewById(R.id.clear_fb);
        ingred_db = FirebaseDatabase.getInstance().getReference("ingredients");

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredientstoFB();
            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id.setText("");
                title.setText("");
                desc.setText("");
                effects.setText("");
                addedin.setText("");
                imageurl.setText("");
            }
        });

    }

    public void addIngredientstoFB()
    {
        String id_add = id.getText().toString();
        String title_add = title.getText().toString();
        String desc_add = desc.getText().toString();
        String effects_add = effects.getText().toString();
        String harm_add = harmful.getText().toString();
        String addedin_add = addedin.getText().toString();
        String imageurl_add = imageurl.getText().toString();

        Ingredients ingredients = new Ingredients(title_add, desc_add
        ,effects_add,harm_add,addedin_add,imageurl_add);

        ingred_db.child(id_add).setValue(ingredients);
        Toast.makeText(this, "values set", Toast.LENGTH_SHORT).show();
    }

}
