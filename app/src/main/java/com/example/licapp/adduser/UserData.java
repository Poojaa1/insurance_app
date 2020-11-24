package com.example.licapp.adduser;

public class UserData {
    public String name,email,key,age,phonenumber,time,date;


    public UserData(String name, String email, String key, String age, String phonenumber,String time, String date) {
        this.name = name;
        this.email = email;
        this.key = key;
        this.age = age;
        this.phonenumber = phonenumber;
        this.time = time;
        this.date = date;}

    public UserData(String name, String email, String downloadUrl, String uniqueKey, String age, String phonenumber, String time, String date) {
    }

    public String getName() {
        return name;
    }

    public void getName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void getEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAge() {
        return age;
    }

    public void getAge(String age) {
        this.age = age;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void getPhonenumber(String date) {
        this.phonenumber = phonenumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
