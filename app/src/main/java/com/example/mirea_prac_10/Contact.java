package com.example.mirea_prac_10;

public class Contact {
    public int id; //идентификатор
    public String name;//имя
    public String phone;//номер телефона

    public Contact() {
        this.id = 0;
        this.name = "";
        this.phone = "";
    }

    public Contact(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }





}
