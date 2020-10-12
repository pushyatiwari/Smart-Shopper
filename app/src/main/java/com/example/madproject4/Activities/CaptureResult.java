package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.madproject4.Adapters.MyAdapter;
import com.example.madproject4.R;

import java.util.ArrayList;

public class CaptureResult extends AppCompatActivity {
    ArrayList<String> itemsArray;
     ListView itemlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_result);

        itemlistview = findViewById(R.id.captureListView);
        //    itemsArray = getIntent().getSerializableExtra("itemsArray");
        Intent intent = getIntent();

        itemsArray = (ArrayList<String>) getIntent().getSerializableExtra("itemsArray");

        int size1 = itemsArray.size();
        String acetic = "https://images.unsplash.com/photo-1572635148818-ef6fd45eb394?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2000&q=80";
        String url = "https://images.unsplash.com/photo-1519996529931-28324d5a630e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1868&q=80";
        String alomonds = "https://images.unsplash.com/photo-1508061253366-f7da158b6d46?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80";
        String alum = "https://images.unsplash.com/photo-1574163783773-39f4681829b0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80";
        String mDescription[] = {"Substance is harmless...", "good source of uns...", "can cause kidney...", "basic elements ...", "Variety of pepper...",
                "Substance is harmless...", "good source of uns...", "can cause kidney...", "basic elements ...", "Variety of pepper..."};
        String images[] = new String[size1];
        int flag = 0;
        for (int i = 0; i < size1; i++) {
            if (flag == 0) {
                images[i] = acetic;
                flag = 1;
            } else if (flag == 1) {
                images[i] = url;
                flag = 2;
            } else if (flag == 2) {
                images[i] = alum;
                flag = 0;
            }
        }
        String desc[] = new String[size1];

        for (int i = 0; i < size1; i++) {
            desc[i] = mDescription[i % mDescription.length];
        }

        String mTitle[] = new String[size1];
        for (int i = 0; i < size1; i++) {
            mTitle[i] = itemsArray.get(i);
        }

        MyAdapter adapter = new MyAdapter(this, mTitle, desc, images);
        itemlistview.setAdapter(adapter);
        itemlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent i = new Intent(CaptureResult.this, ShowDetailsCapture.class);
                startActivity(i);
            }
        });
    }
}
