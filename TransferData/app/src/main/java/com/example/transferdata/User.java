package com.example.transferdata;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int age;
    private String gender;
    private Boolean sing;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getSing() {
        return sing;
    }

    public void setSing(Boolean sing) {
        this.sing = sing;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", sing=" + sing +
                '}';
    }
}
