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
        String acetate = "https://img.rolandberger.com/content_assets/content_images/captions/rb_pub_17_011_foc_us_chemical_winners_image_image_caption_none.jpg";
        String url = "https://s.alicdn.com/@sc01/kf/HTB1IwrUd25G3KVjSZPxq6zI3XXag.jpg_300x300.jpg";
        String AluminiumSodiumsulfte = "https://5.imimg.com/data5/BP/AF/MY-26952425/textile-auxiliaries-chemical-500x500.jpg";
        String mDescription[] = {"Substance is harmless...", "good source of uns...", "can cause kidney...", "basic elements ...", "Variety of pepper...",
                "Substance is harmless...", "good source of uns...", "can cause kidney...", "basic elements ...", "Variety of pepper..."};


        String images[] = new String[size1];
        int flag = 0;
        for (int i = 0; i < size1; i++) {
            if (flag == 0) {
                images[i] = acetate;
                flag = 1;
            } else if (flag == 1) {
                images[i] = url;
                flag = 2;
            } else if (flag == 2) {
                images[i] = AluminiumSodiumsulfte;
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
