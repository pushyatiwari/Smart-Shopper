package com.example.madproject4.Model;

public class Food {
    private String name;
    private String brand_name;
    private String nf_calories;
    private String thumb;
    private String nix_item_id;

    public Food(){}
    public Food(String n,String brd_nm,String nf_cal,String thmb_n,String nx_item_id)
    {
        name = n;
        brand_name = brd_nm;
        nf_calories = nf_cal;
        thumb = thmb_n;
        nix_item_id = nx_item_id;
    }

    public String getName() {
        return name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public String getNf_calories() {
        return nf_calories;
    }

    public String getThumb() {
        return thumb;
    }

    public String getNix_item_id() {
        return nix_item_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public void setNf_calories(String nf_calories) {
        this.nf_calories = nf_calories;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setNix_item_id(String nix_item_id) {
        this.nix_item_id = nix_item_id;
    }
}


/*
  "food_name": "Grilled Cheese",
            "serving_unit": "serving",
            "nix_brand_id": "5e4175e4ac4757343cb0a2b5",
            "brand_name_item_name": "Wahlburgers Grilled Cheese",
            "serving_qty": 1,
            "nf_calories": 210,
            "photo": {
                "thumb": "https://d2eawub7utcl6.cloudfront.net/images/nix-apple-grey.png",
                "highres": null,
                "is_user_uploaded": false
            },
            "brand_name": "Wahlburgers",
            "region": 1,
            "brand_type": 1,
            "nix_item_id": "1521950510dff56c82dde506",
            "locale": "en_US"
        },
* */
