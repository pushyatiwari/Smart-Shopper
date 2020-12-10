package com.example.madproject4.Model;

public class Ingredients {
    String title;
    String description;
    String effects;
    String harmful;
    String addedIn;
    String imageUrl;

    public Ingredients(){

    }

    public Ingredients(String title, String description, String effects, String harmful, String addedIn, String imageUrl) {
        this.title = title;
        this.description = description;
        this.effects = effects;
        this.harmful = harmful;
        this.addedIn = addedIn;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getEffects() {
        return effects;
    }

    public String getHarmful() {
        return harmful;
    }

    public String getAddedIn() {
        return addedIn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }

    public void setHarmful(String harmful) {
        this.harmful = harmful;
    }

    public void setAddedIn(String addedIn) {
        this.addedIn = addedIn;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
