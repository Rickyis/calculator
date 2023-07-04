package com.example.myapplication;

public class diary{
    private String title;
    private String content;
    private String time;
    private String author;

    public diary(String title, String content, String time, String author) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.author = author;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
