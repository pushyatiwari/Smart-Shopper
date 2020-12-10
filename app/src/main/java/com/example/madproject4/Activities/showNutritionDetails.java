package com.example.madproject4.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class showNutritionDetails extends BaseActivity {
//    <!--    "nf_calories": 570,-->
//<!--    "nf_total_fat": 29,-->
//<!--    "nf_saturated_fat": 12,-->
//<!--    "nf_cholesterol": 45,-->
//<!--    "nf_sodium": 1680,-->
//<!--    "nf_total_carbohydrate": 61,-->
//<!--    "nf_dietary_fiber": 2,-->
//<!--    "nf_sugars": 11,-->
//<!--    "nf_protein": 21,-->
//<!--    "nf_potassium": null,-->
     TextView caltxt, fat_txt, sfat_txt,chol_txt,sod_txt,carb_txt,diber_txt,sugars_txt,protein_txt,pott_txt
        , food_api_title;
    String base_url = "https://trackapi.nutritionix.com/v2/search/item?nix_item_id=";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_nutrition_details);
        String n_id = getIntent().getStringExtra("nix_id");
        String food_nix_name = getIntent().getStringExtra("nix_name");
        caltxt = (TextView) findViewById(R.id.cal);
        fat_txt = (TextView) findViewById(R.id.fat);
        sfat_txt = (TextView) findViewById(R.id.sat_fat);
        chol_txt = (TextView) findViewById(R.id.cholestrol);
        sod_txt = (TextView) findViewById(R.id.sodium);
        carb_txt = (TextView) findViewById(R.id.carbohydrate);
        diber_txt = (TextView) findViewById(R.id.dietary_fibre);
        sugars_txt = (TextView) findViewById(R.id.sugar);
        protein_txt = (TextView) findViewById(R.id.protein);
        pott_txt = (TextView) findViewById(R.id.potassium);
        food_api_title = (TextView) findViewById(R.id.food_api_title);
        food_api_title.setText(food_nix_name);
        jsonrequest(base_url+n_id);

    }

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

                        cal = cal + "kcal";
                        tf = tf + "g";
                        sf = sf + "g";
                        chl = chl + "mg";
                        sod = sod + "mg";
                        cb = cb + "g";
                        df = df + "g";
                        sg = sg + "g";
                        pt = pt + "g";
                        pts = pts + "mg";

                        caltxt.setText(cal );
                        fat_txt.setText(tf);
                        sfat_txt.setText(sf);
                        chol_txt.setText(chl);
                        sod_txt.setText(sod);
                        carb_txt.setText(cb);
                        diber_txt.setText(df);
                        sugars_txt.setText(sg);
                        protein_txt.setText(pt);
                        pott_txt.setText(pts);




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
