package com.example.demo.entity;

public class Te {
    private String name;
    private String city;
    private int tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public Te(String name, String city, int tel) {
        this.name = name;
        this.city = city;
        this.tel = tel;
    }
}
