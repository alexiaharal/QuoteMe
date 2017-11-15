package com.example.alexiah.quoteme;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Alexiah on 17/07/2017.
 */

public class Person implements Serializable {
    private String personId= UUID.randomUUID().toString();
    private String name;
    private String surname;
    private String dateOfBirth;
    private String phone;
    private String email;

    private int mData;

    public Person(){
        this.name="";
        this.surname="";
        this.dateOfBirth="";
        this.phone="";
        this.email="";
    }

    public Person(String name, String surname, String dateOfBirth, String phone, String email){
        this.name=name;
        this.surname=surname;
        this.dateOfBirth=dateOfBirth;
        this.phone=phone;
        this.email=email;
    }


    /* Getters  - Setters*/

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    }
