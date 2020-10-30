package com.example.madproject4.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madproject4.Database.DatabaseHelper;
import com.example.madproject4.R;

public class AddDataToDatabase extends BaseActivity {
    DatabaseHelper myDb;
    private EditText id, title, desc, effects, harmful, addedin, imageurl;
    private Button btnAddData;
    Button btnviewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_to_database);
        myDb = new DatabaseHelper(this);
        id = (EditText) findViewById(R.id.id);
        title = (EditText) findViewById(R.id.title);
        desc = (EditText) findViewById(R.id.desc);
        effects = (EditText) findViewById(R.id.effects);
        harmful = (EditText) findViewById(R.id.harmful);
        addedin = (EditText) findViewById(R.id.addedin);
        imageurl = (EditText) findViewById(R.id.imageurl);
        btnAddData = (Button) findViewById(R.id.insert_dbbtn);
        btnviewAll = (Button) findViewById(R.id.view_dbbtn);

        AddData();
        viewAll();

    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(
                                id.getText().toString(),
                                title.getText().toString(),
                                desc.getText().toString(),
                                effects.getText().toString(),
                                harmful.getText().toString(),
                                addedin.getText().toString(),
                                imageurl.getText().toString()
                                );
                        if (isInserted == true)
                            Toast.makeText(AddDataToDatabase.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddDataToDatabase.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("title :" + res.getString(1) + "\n");
                            buffer.append("description :" + res.getString(2) + "\n");
                            buffer.append("effects :" + res.getString(3) + "\n");
                            buffer.append("harmful :" + res.getString(4) + "\n");
                            buffer.append("added in :" + res.getString(5) + "\n\n");
                            buffer.append("image url" + res.getString(6) + "\n\n");


                        }
                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }



    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void idGet(View view) {

        Cursor res = myDb.getId(title.getText().toString());
        if (res.getCount() == 0) {
            // show message
            showMessage("Error", "Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :" + res.getString(0) + "\n");
        }



        // Show all data
        showMessage("Data", buffer.toString());
    }
}