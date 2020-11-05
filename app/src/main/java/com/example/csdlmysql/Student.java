package com.example.csdlmysql;

public class Student {
    private int id;
    private String name;
    private String lop;
    private String phone;
    private String email;

    public Student() {
    }

    public Student(String name, String lop, String phone, String email) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.lop = lop;
    }

    public Student(int id,String name, String lop,String phone, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.lop = lop;
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

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getlop() {
        return lop;
    }

    public void setlop(String lop) {
        this.lop = lop;
    }
}
