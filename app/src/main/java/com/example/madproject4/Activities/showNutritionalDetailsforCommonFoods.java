package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.madproject4.R;

import java.util.HashMap;

public class showNutritionalDetailsforCommonFoods extends AppCompatActivity {
    HashMap<String, String> fullnutr_mp = new HashMap<>();
    TextView protein_cftext, carb_cftext, energy_cftext,vita_cftext,vitd_cftext,
            vite_cftext,vitc_cftxt,sugar_cftext,calcium_cftxt, title_cf, servsize_cf;
    RequestOptions option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_nutritional_detailsfor_common_foods);
        title_cf = findViewById(R.id.common_food_title);
        servsize_cf = findViewById(R.id.serving_size_cf);
        protein_cftext = findViewById(R.id.protein_cf);
        carb_cftext = findViewById(R.id.carb_cf);
        energy_cftext = findViewById(R.id.energy_cf);
        vita_cftext = findViewById(R.id.vita_cf);
        vitd_cftext = findViewById(R.id.vitd_cf);
        vite_cftext = findViewById(R.id.vite_cf);
        vitc_cftxt = findViewById(R.id.vitc_cf);
        sugar_cftext = findViewById(R.id.sugar_cf);
        calcium_cftxt = findViewById(R.id.calcium_cf);
        ImageView imageView = findViewById(R.id.nutri_detail_thumb_cf);
        Intent i = getIntent();
        option = new RequestOptions().centerCrop().placeholder(R.drawable.common_full_open_on_phone).error(R.drawable.common_google_signin_btn_icon_dark);

        HashMap<String, String> attr_ids = (HashMap<String, String>) i.getSerializableExtra("nutri_map");
        String title = i.getStringExtra("nutri_title");
        String qty = i.getStringExtra("nutri_qty");
        String unit = i.getStringExtra("nutri_unit");
        String thumb = i.getStringExtra("cf_thumb");
        String gram = i.getStringExtra("nutri_gram");
        title_cf.setText(title);
        servsize_cf.setText("Serving: "+qty+"\n unit: "+unit+"\nserving weight gram: "+gram);
        fullnutr_mp.put("204","Total lipid(fat)");
        fullnutr_mp.put("208","Energy");
        fullnutr_mp.put("301","Calcium");
        fullnutr_mp.put("324","Vitamin D");
        fullnutr_mp.put("401","Vitamin C, total ascorbic acid");
        fullnutr_mp.put("318","Vitamin A, IU");
        fullnutr_mp.put("203","Protein");
        fullnutr_mp.put("205","Carbohydrate");
        fullnutr_mp.put("210","Sucrose");
        fullnutr_mp.put("269","Sugar");
        protein_cftext.setText(attr_ids.get("203"));
        carb_cftext.setText(attr_ids.get("205"));
        energy_cftext.setText(attr_ids.get("208"));
        vita_cftext.setText(attr_ids.get("318"));
        vitd_cftext.setText(attr_ids.get("324"));
        vite_cftext.setText(attr_ids.get("573"));
        vitc_cftxt.setText(attr_ids.get("401"));
        sugar_cftext.setText(attr_ids.get("269"));
        calcium_cftxt.setText(attr_ids.get("301"));
        Glide.with(this).load(thumb).apply(option).into(imageView);

    }
    public void showDailyRecommendedValues(View view) {
        startActivity(new Intent(showNutritionalDetailsforCommonFoods.this, show_daily_recommended_values.class));
    }
}
