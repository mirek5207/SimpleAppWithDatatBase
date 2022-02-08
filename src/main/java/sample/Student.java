package sample;

public class Student {
    private int id;
    private String surName, name;

    public Student(int id, String surName, String name) {
        this.id = id;
        this.surName = surName;
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getsurName() {
        return surName;
    }

    public void setsurName(String surName) {
        this.surName = surName;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
}
