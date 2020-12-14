package com.example.madproject4.Activities;

import androidx.annotation.NonNull;
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
import com.example.madproject4.Fragments.FragmentTakePicture;
import com.example.madproject4.Model.Ingredients;
import com.example.madproject4.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CaptureResult extends BaseActivity {
    ArrayList<Ingredients> itemsArray;
     ListView itemlistview;
    // DatabaseHelper myDb;
    String[] mdesc;
    String[] mimages;
    String[] mTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_result);
       // myDb = new DatabaseHelper(this);
        itemlistview = findViewById(R.id.captureListView);
        //    itemsArray = getIntent().getSerializableExtra("itemsArray");

        itemsArray = FragmentTakePicture.itemsArraylist;

        int size = itemsArray.size();
         mTitle = new String[size];
      mdesc = new String[size];
       mimages= new String[size];


//        for(int i = 0; i < itemsArray.size();i++)
//        {
//           String title = itemsArray.get(i);
//           Cursor res =  myDb.getData(title);
//           mTitle[i] = title;
//            while (res.moveToNext()) {
//
//                     mdesc[i] = res.getString(2).substring(0,50) + "...";
//                      mimages[i] = res.getString(6);
//                      break;
//
//            }
//
//        }


            for (int i = 0; i < size; i++) {
                mTitle[i] = itemsArray.get(i).getTitle();
                mdesc[i] = itemsArray.get(i).getDescription();
                mimages[i] = itemsArray.get(i).getImageUrl();
                Log.d("ingred data", "onDataChange: " + mTitle[i] + ", " +
                        mdesc[i] + " , ");
            }

            MyAdapter adapter = new MyAdapter(this, mTitle, mdesc, mimages);
            itemlistview.setAdapter(adapter);

            itemlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    Intent i_newshowdetails = new Intent(CaptureResult.this, FirebaseIndividualIngredDetails.class);
                    i_newshowdetails.putExtra("title", mTitle[position]);
                    i_newshowdetails.putExtra("index", position);
                    //Toast.makeText(CaptureResult.this, mTitle[position], Toast.LENGTH_SHORT).show();
                    startActivity(i_newshowdetails);
                }
            });


    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
