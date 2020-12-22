package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.madproject4.R;

import java.util.HashMap;

public class showNutritionalDetailsforCommonFoods extends AppCompatActivity {
    HashMap<String, String> fullnutr_mp = new HashMap<>();
    TextView protein_cftext, carb_cftext, energy_cftext,vita_cftext,vitd_cftext,
            vite_cftext,vitc_cftxt,sugar_cftext,calcium_cftxt, title_cf, servsize_cf;
    RequestOptions option;
    HashMap<String, String> attr_ids;
    public static final String MyPREFERENCES = "myFoodNutritionPrefs" ;
    public static final String protein_pref = "Protein";
    public static final String carb_pref = "Carbohydrate";
    public static final String enrgy_pref = "Energy";
    public static final String vita_pref = "Vitamin A";
    public static final String vitd_pref = "Vitamin D";
    public static final String vite_pref = "Vitamin E";
    public static final String vitc_pref = "Vitamin C";
    public static final String sugar_pref = "Sugar";
    public static final String calcium_pref = "Calcium";
    public static final String calories_pref = "Calories";
    SharedPreferences sharedpreferences;

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

        attr_ids = (HashMap<String, String>) i.getSerializableExtra("nutri_map");
        String title = i.getStringExtra("nutri_title");
        String qty = i.getStringExtra("nutri_qty");
        String unit = i.getStringExtra("nutri_unit");
        String thumb = i.getStringExtra("cf_thumb");
        String gram = i.getStringExtra("nutri_gram");
        title_cf.setText(title);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

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

    public void addtofooddatabase(View view) {
        Toast.makeText(this, "Added, to check details, check out daily nutrition track", Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        float temp_prot_prefd = 0, temp_energy_prefd =0, temp_vita_prefd = 0,temp_vitd_prefd = 0,
                temp_vite_prefd = 0,temp_vitc_prefd =0,temp_sugar_prefd = 0,temp_calcium_prefd = 0,
                temp_cal_prefd = 0,temp_carb_prefd =0;
        ;
        try {
            Float temp_prot_pref = sharedpreferences.getFloat(protein_pref, 0);
            temp_prot_prefd = (temp_prot_pref + Float.parseFloat(attr_ids.get("203")));
        }
        catch (Exception e)
        { }
        try{
        Float temp_energy_pref = sharedpreferences.getFloat(enrgy_pref,0);
         temp_energy_prefd =(temp_energy_pref + Float.parseFloat(attr_ids.get("208")));}
        catch (Exception e){}
        try{
        Float temp_vita_pref = sharedpreferences.getFloat(vita_pref,0);
         temp_vita_prefd =(temp_vita_pref + Float.parseFloat(attr_ids.get("318")));}
        catch (Exception e){}
        try {
            Float temp_vitd_pref = sharedpreferences.getFloat(vitd_pref, 0);
            temp_vitd_prefd = (temp_vitd_pref + Float.parseFloat(attr_ids.get("324")));
        }catch (Exception e){}
        try{
        Float temp_vite_pref = sharedpreferences.getFloat(vite_pref,0);
         temp_vite_prefd =(temp_vite_pref + Float.parseFloat(attr_ids.get("573")));}
        catch (Exception e){}
        try {
            Float temp_vitc_pref = sharedpreferences.getFloat(vitc_pref, 0);
            temp_vitc_prefd = (temp_vitc_pref + Float.parseFloat(attr_ids.get("401")));
        }catch (Exception e){}
        try {
            Float temp_sugar_pref = sharedpreferences.getFloat(sugar_pref, 0);
            temp_sugar_prefd = (temp_sugar_pref + Float.parseFloat(attr_ids.get("269")));
        }catch (Exception e){}
        try {
            Float temp_calcium_pref = sharedpreferences.getFloat(calcium_pref, 0);
            temp_calcium_prefd = (temp_calcium_pref + Float.parseFloat(attr_ids.get("301")));
        }catch (Exception e){}
        try {
            Float temp_cal_pref = sharedpreferences.getFloat(calories_pref, 0);
            temp_cal_prefd = (temp_cal_pref + Float.parseFloat(attr_ids.get("208")));
        }catch (Exception e)
        {

        }
        try {
            Float temp_card_pref = sharedpreferences.getFloat(carb_pref, 0);
            temp_carb_prefd = (temp_card_pref + Float.parseFloat(attr_ids.get("205")));
        }catch (Exception e)
        {

        }


        editor.putFloat(protein_pref, temp_prot_prefd);
        editor.putFloat(carb_pref, temp_carb_prefd);
        editor.putFloat(enrgy_pref, temp_energy_prefd);
        editor.putFloat(vita_pref,temp_vita_prefd);
        editor.putFloat(vitd_pref, temp_vitd_prefd);
        editor.putFloat(vite_pref, temp_vite_prefd);
        editor.putFloat(vitc_pref, temp_vitc_prefd);
        editor.putFloat(sugar_pref, temp_sugar_prefd);
        editor.putFloat(calcium_pref, temp_calcium_prefd);
       editor.putFloat(calories_pref,temp_cal_prefd);
       editor.commit();
    }

}
