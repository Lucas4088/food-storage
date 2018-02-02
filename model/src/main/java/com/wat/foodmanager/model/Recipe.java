package com.wat.foodmanager.model;

public class Recipe {
    private String id;
    private String imageUrl;
    private String sourceUrl;
    private String f2fUrl;
    private String title;
    private String publisher;
    private String publisherUrl;
    private double socialRank;
    private int page;
    private String[] ingredients;

    public Recipe(String id, String imageUrl, String sourceUrl, String f2fUrl, String title, String publisher, String publisherUrl, double socialRank, int page, String[] ingredients) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.sourceUrl = sourceUrl;
        this.f2fUrl = f2fUrl;
        this.title = title;
        this.publisher = publisher;
        this.publisherUrl = publisherUrl;
        this.socialRank = socialRank;
        this.page = page;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getF2fUrl() {
        return f2fUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublisherUrl() {
        return publisherUrl;
    }

    public double getSocialRank() {
        return socialRank;
    }

    public int getPage() {
        return page;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return title;
    }
}
