package com.journaldev.data;

public class Student {
    private String id;
    private String name;
    private String dept;

    public Student(String number, String jim, String it) {
        this.id = number;
        this.name = jim;
        this.dept = it;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
