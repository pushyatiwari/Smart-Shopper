package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.madproject4.Adapters.MyAdapter;
import com.example.madproject4.Database.DatabaseHelper;
import com.example.madproject4.R;

import java.util.ArrayList;

public class CaptureResult extends BaseActivity {
    ArrayList<String> itemsArray;
     ListView itemlistview;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_result);
        myDb = new DatabaseHelper(this);
        itemlistview = findViewById(R.id.captureListView);
        //    itemsArray = getIntent().getSerializableExtra("itemsArray");

        itemsArray = (ArrayList<String>) getIntent().getSerializableExtra("itemsArray");
        int size = itemsArray.size();
       final String mTitle[] = new String[size];
        String mdesc[] = new String[size];
        String mimages[] = new String[size];

        for(int i = 0; i < itemsArray.size();i++)
        {
           String title = itemsArray.get(i);
           Cursor res =  myDb.getData(title);
           mTitle[i] = title;
            while (res.moveToNext()) {

                     mdesc[i] = res.getString(2).substring(0,20);
                      mimages[i] = res.getString(6);
                      break;

            }

        }
        MyAdapter adapter = new MyAdapter(this, mTitle, mdesc, mimages);
        itemlistview.setAdapter(adapter);
        itemlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent i_newshowdetails = new Intent(CaptureResult.this, ShowDetailsCapture.class);
                i_newshowdetails.putExtra("title",mTitle[position]);
                //Toast.makeText(CaptureResult.this, mTitle[position], Toast.LENGTH_SHORT).show();
                startActivity(i_newshowdetails);
            }
        });
    }
}
