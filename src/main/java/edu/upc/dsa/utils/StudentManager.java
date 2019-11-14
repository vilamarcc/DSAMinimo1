package edu.upc.dsa.utils;
import edu.upc.dsa.models.Student;

import java.util.List;

public interface StudentManager {
    public void addStudent(String name, String lastname, String dni);
    public void deleteStudent(String dni);
    public void enrollStudent(String dni, String subject);
    public List<String> getSubjectsStudent(String dni);
    public void removeSubject(String dni, String subject);
    public Student getStudent(String dni);
    public List<Student> getAllStudents();
    public int getNumStudents();
}
