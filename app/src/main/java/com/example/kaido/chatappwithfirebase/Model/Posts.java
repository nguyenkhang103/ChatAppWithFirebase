package com.example.kaido.chatappwithfirebase.Model;

public class Posts {
    private String postid;
    private String postimage;
    private String description;
    private String author;

    public Posts(String postid, String postimage, String description, String author) {
        this.postid = postid;
        this.postimage = postimage;
        this.description = description;
        this.author = author;
    }

    public Posts() {
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPostimage() {
        return postimage;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
