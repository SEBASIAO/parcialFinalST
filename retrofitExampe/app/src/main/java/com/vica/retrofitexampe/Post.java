package com.vica.retrofitexampe;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("author")
    private String author;
    @SerializedName("description")
    private String description;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}
