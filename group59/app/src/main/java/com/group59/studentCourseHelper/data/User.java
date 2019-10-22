package com.group59.studentCourseHelper.data;

public class User {
    private String name;
    private String imageurl;
    private String email;
    private String password;
    private String uid;

    public User() {
    }

    public User(String name, String imageurl, String email, String password, String uid) {
        this.name = name;
        this.imageurl = imageurl;
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
