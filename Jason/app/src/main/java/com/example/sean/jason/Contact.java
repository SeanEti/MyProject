package com.example.sean.jason;

/**
 * Created by Sean on 8/10/2017.
 */

public class Contact {
    private String name,mail;
    private int age,phone,id;

    public Contact(String name,String mail,int age,int phone,int id){
        this.name = name;
        this.mail = mail;
        this.age = age;
        this.id = id;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
