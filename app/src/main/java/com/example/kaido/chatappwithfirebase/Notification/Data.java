package com.example.kaido.chatappwithfirebase.Notification;

public class Data {
    private String user;
    private int icon;
    private String tittle;
    private String content;
    private String sented;

    public Data(String user, int icon, String tittle, String content,String sented) {
        this.user = user;
        this.icon = icon;
        this.tittle = tittle;
        this.content = content;
        this.sented = sented;
    }

    public Data() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSented() {
        return sented;
    }

    public void setSented(String sented) {
        this.sented = sented;
    }
}
