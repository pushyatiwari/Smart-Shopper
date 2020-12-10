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
    Button btnviewAll;
    DatabaseReference ingred_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_fb);
        id = (EditText) findViewById(R.id.id_fb);
        title = (EditText) findViewById(R.id.title_fb);
        desc = (EditText) findViewById(R.id.desc_fb);
        effects = (EditText) findViewById(R.id.effects_fb);
        harmful = (EditText) findViewById(R.id.harmful_fb);
        addedin = (EditText) findViewById(R.id.addedin_fb);
        imageurl = (EditText) findViewById(R.id.imageurl_fb);
        btnAddData = (Button) findViewById(R.id.insert_dbbtn_fb);
        btnviewAll = (Button) findViewById(R.id.view_dbbtn_fb);
        ingred_db = FirebaseDatabase.getInstance().getReference("ingredients");

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredientstoFB();
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
