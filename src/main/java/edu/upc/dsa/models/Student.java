package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Student {
    String name;
    String lastname;
    String dni;
    LinkedList<String> subjects;

    public Student(String n, String ln, String dnii) {
        this.name = n;
        this.lastname = ln;
        this.dni = dnii;
        this.subjects = new LinkedList<String>();
    }

    public Student(){}

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }

    public String getDni() {
        return dni;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubjects(LinkedList<String> subjects) {
        this.subjects = subjects;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void enroll(String subject){
        this.subjects.add(subject);
    }

    public LinkedList<String> getSubjects() {
        return subjects;
    }
}
