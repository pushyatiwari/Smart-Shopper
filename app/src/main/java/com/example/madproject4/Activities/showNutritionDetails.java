package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.madproject4.Model.Food;
import com.example.madproject4.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class showNutritionDetails extends BaseActivity {
     TextView caltxt, fat_txt, sfat_txt,chol_txt,sod_txt,carb_txt,diber_txt,sugars_txt,protein_txt,pott_txt
        , food_api_title, serv_size_txtv,lfat_txt, energy_txt, calcium_txt,vitd_txt,vitc_txt,vita_txt;
    String thmb;
    String base_url = "https://trackapi.nutritionix.com/v2/search/item?nix_item_id=";
    private RequestQueue requestQueue;
    ImageView imageView;
    HashMap<String, String> fullnutr_mp = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_nutrition_details);
        String n_id = getIntent().getStringExtra("nix_id");
        String food_nix_name = getIntent().getStringExtra("nix_name");
        thmb = getIntent().getStringExtra("nix_thumb");
        imageView = findViewById(R.id.nutri_detail_thumb);
        caltxt = findViewById(R.id.cal);
        fat_txt = findViewById(R.id.fat);
        sfat_txt = findViewById(R.id.sat_fat);
        chol_txt = findViewById(R.id.cholestrol);
        sod_txt = findViewById(R.id.sodium);
        carb_txt = findViewById(R.id.carbohydrate);
        diber_txt = findViewById(R.id.dietary_fibre);
        sugars_txt = findViewById(R.id.sugar);
        protein_txt = findViewById(R.id.protein);
        pott_txt = findViewById(R.id.potassium);
        //
        calcium_txt = findViewById(R.id.cal);
        energy_txt = findViewById(R.id.energy);
        lfat_txt = findViewById(R.id.tlfat);
        vitd_txt = findViewById(R.id.vit_d);
        vita_txt = findViewById(R.id.vit_a);
        vitc_txt = findViewById(R.id.vit_c);
        food_api_title = findViewById(R.id.food_api_title);
        serv_size_txtv = findViewById(R.id.serving_size);
        food_api_title.setText(food_nix_name);
        fullnutr_mp.put("204","Total lipid(fat)");
        fullnutr_mp.put("208","Energy");
        fullnutr_mp.put("301","Calcium");
        fullnutr_mp.put("324","Vitamin D");
        fullnutr_mp.put("401","Vitamin C, total ascorbic acid");
        fullnutr_mp.put("318","Vitamin A, IU");



        jsonrequest(base_url+n_id);
//        if(thmb != "https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png")
//        {
//            Picasso.with(this)// Context
//                    .load(thmb).fit().centerCrop()
//                    .into(imageView);
//        }

    }
   String serv_qty, serv_unit,serv_weight_grams;
    private void jsonrequest (String url) {
           JsonObjectRequest req =  new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("foods");
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String cal = jsonObject.getString("nf_calories");
                        String tf = jsonObject.getString("nf_total_fat");
                        String sf = jsonObject.getString("nf_saturated_fat");
                        String chl = jsonObject.getString("nf_cholesterol");
                        String sod = jsonObject.getString("nf_sodium");
                        String cb = jsonObject.getString("nf_total_carbohydrate");
                        String df = jsonObject.getString("nf_dietary_fiber");
                        String sg = jsonObject.getString("nf_sugars");
                        String pt = jsonObject.getString("nf_protein");
                        String pts = jsonObject.getString("nf_potassium");
                        serv_qty = jsonObject.getString("serving_qty");
                    serv_unit = jsonObject.getString("serving_unit");
                   String serv_weight = jsonObject.getString("serving_weight_grams");

                    serv_weight_grams = jsonObject.getString("serving_weight_grams");
                    if(serv_qty == null)
                    {
                        serv_qty = "_";
                    }
                    if(serv_qty == "")
                    {
                        serv_qty = "_";
                    }
                    if(serv_qty.isEmpty())
                    {
                        serv_qty = "_";
                    }
                    if(serv_unit == null)
                    {
                        serv_unit = "_";
                    }
                    if(serv_unit == "")
                    {
                        serv_unit = "_";
                    }
                    if(serv_unit.isEmpty())
                    {
                        serv_unit = "_";
                    }
                    if(serv_weight == null)
                    {
                        serv_weight = "_";
                    }
                    if(serv_weight == "")
                    {
                        serv_weight = "_";
                    }
                    if(serv_weight.isEmpty())
                    {
                        serv_weight = "_";
                    }
                    serv_size_txtv.setText("serving quantity:  "+serv_qty + "\n" +
                                "serving unit: " + serv_unit + "\nserving_weight_grams: " + serv_weight);




                        cal = cal + " kcal";
                        tf = tf + " g";
                        sf = sf + " g";
                        chl = chl + " mg";
                        sod = sod + " mg";
                        cb = cb + " g";
                        df = df + " g";
                        sg = sg + " g";
                        pt = pt + " g";
                        pts = pts + " mg";

                        caltxt.setText(cal );
                        fat_txt.setText(tf);
                        sfat_txt.setText(sf);
                        chol_txt.setText(chl);
                        sod_txt.setText(sod);
                        carb_txt.setText(cb);
                        diber_txt.setText(df);
                        sugars_txt.setText(sg);
                        protein_txt.setText(pt);
                        if(pts == null)
                            pott_txt.setText("0");
                        else
                            pott_txt.setText(pts);
                    JSONArray full_nutriets = jsonObject.getJSONArray("full_nutrients");
                    for (int i = 0;i < full_nutriets.length();i++)
                    {
                        JSONObject jo = full_nutriets.getJSONObject(i);
                        String attr = jo.getString("attr_id");
                        String val = jo.getString("value");
                        if(fullnutr_mp.containsKey(attr))
                        {
                            String nut_name = fullnutr_mp.get(attr);
                            if(nut_name == "Total lipid(fat)")
                            {
                                lfat_txt.setText(val + "g");
                            }
                            else if(nut_name == "Energy")
                            {
                                energy_txt.setText(val + "kcal");
                            }
                            else if(nut_name == "Calcium")
                            {
                                calcium_txt.setText(val + "mg");
                            }
                            else if(nut_name == "Vitamin D")
                            {
                                vitd_txt.setText(val + "IU");
                            }
                            else if(nut_name == "Vitamin C, total ascorbic acid")
                        {
                            vitc_txt.setText(val + "mg");
                        }
                        else if(nut_name == "Vitamin A, IU")
                        {
                            vita_txt.setText(val + "IU");
                        }

                        }

                        Log.d("full nutrients: ", "onResponse: " +attr +", "+val );
                    }






                    Log.d("Response food", response.getString("branded"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error", "Error: " + error.getMessage());
                Log.d("error food", "error");

            }
        })
        {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("x-app-id ", "fc2dc68b");
                headers.put("x-app-key", "655b6f137722571fb6a530c658656595");
                return headers;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);


    }

    public void showDailyRecommendedValues(View view) {
       startActivity(new Intent(showNutritionDetails.this, show_daily_recommended_values.class));
    }
}
