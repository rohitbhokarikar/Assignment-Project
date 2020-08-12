package com.assignment.com;

public class ListItem {
    private String name;
    private String country;
    private String phone;
    private String dob;
    private String profileImage;
    private String email;

    public ListItem(String name, String country, String phone, String dob,String profileImage,String email) {
        this.name = name;
        this.country = country;
        this.phone = phone;
        this.dob = dob;
        this.profileImage =profileImage;
        this.email= email;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getEmail() {
        return email;
    }
}
