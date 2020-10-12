package com.example.madproject4.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.madproject4.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class MyAdapter extends ArrayAdapter<String> {

    Context context;
    String rTitle[];
    String rDescription[];
    String rImgs[];

    public MyAdapter(Context c, String title[], String description[], String imgs[]) {
        super(c, R.layout.row, R.id.listItemText, title);
        this.context = c;
        this.rTitle = title;
        this.rDescription = description;
        this.rImgs = imgs;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row, parent, false);
        ImageView images = row.findViewById(R.id.imageListItem);
        TextView myTitle = row.findViewById(R.id.listItemText);
        TextView myDescription = row.findViewById(R.id.listItemDesc);

        // now set our resources on views

        Picasso.with(context)// Context
                .load(rImgs[position]).fit().centerCrop() // URL or file
                .into(images); // An ImageView object to show the loaded image
        myTitle.setText(rTitle[position]);
        myDescription.setText(rDescription[position]);
    return row;
    }

}