package com.malik.jsonplaceholdergradle;

class Post {

    private int id;
    private int userId;
    private String title;
    private String body;

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    int getUserId() {
        return userId;
    }

    void setUserId(int userId) {
        this.userId = userId;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getBody() {
        return body;
    }

    void setBody(String body) {
        this.body = body;
    }

    String toJson() {
        return "{" +
                "\"id\": " + id +
                ", \"userId\": " + userId +
                ", \"title\": \"" + title + "\"" +
                ", \"body\": \"" + body.replace(System.lineSeparator(), "\\n") + "\"" +
                "}";
    }
}