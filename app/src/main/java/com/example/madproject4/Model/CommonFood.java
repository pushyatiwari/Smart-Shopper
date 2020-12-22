package com.example.madproject4.Model;

import java.util.HashMap;

public class CommonFood {
    private String name;
    private String serving_qty;
    private String serving_unit;
    private String serving_weight_grams;
    private String thumb;
    private HashMap<String,String> attr_id = new HashMap<>();

    public CommonFood() {
    }

    public CommonFood(String name) {
        this.name = name;
    }

    public CommonFood(String name, String serving_qty, String serving_unit, String serving_weight_grams, String thumb, HashMap<String, String> attr_id) {
        this.name = name;
        this.serving_qty = serving_qty;
        this.serving_unit = serving_unit;
        this.serving_weight_grams = serving_weight_grams;
        this.thumb = thumb;
        this.attr_id = attr_id;
    }

    public CommonFood(String name, String serving_qty, String serving_unit, String serving_weight_grams, HashMap<String, String> attr_id) {
        this.name = name;
        this.serving_qty = serving_qty;
        this.serving_unit = serving_unit;
        this.serving_weight_grams = serving_weight_grams;
        this.attr_id = attr_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServing_qty() {
        return serving_qty;
    }

    public void setServing_qty(String serving_qty) {
        this.serving_qty = serving_qty;
    }

    public String getServing_unit() {
        return serving_unit;
    }

    public void setServing_unit(String serving_unit) {
        this.serving_unit = serving_unit;
    }

    public String getServing_weight_grams() {
        return serving_weight_grams;
    }

    public void setServing_weight_grams(String serving_weight_grams) {
        this.serving_weight_grams = serving_weight_grams;
    }

    public HashMap<String, String> getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(HashMap<String, String> attr_id) {
        this.attr_id = attr_id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
