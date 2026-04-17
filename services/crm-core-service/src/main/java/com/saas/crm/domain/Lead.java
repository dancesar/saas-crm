package com.saas.crm.domain;

public class Lead {

    private String name;
    private String email;
    private String phone;

    public Lead(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
