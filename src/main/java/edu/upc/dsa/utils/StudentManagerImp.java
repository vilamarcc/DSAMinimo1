package edu.upc.dsa.utils;

import edu.upc.dsa.models.Student;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class StudentManagerImp implements StudentManager {

    private HashMap<String, Student> allstudents;
    final static Logger logger = Logger.getLogger(StudentManagerImp.class);

    private static StudentManager instance;


    private StudentManagerImp(){
        this.allstudents = new HashMap<String, Student>();
    }

    public static StudentManager getInstance() {
        if (instance==null) instance = new StudentManagerImp();
        return instance;
    }

    @Override
    public void addStudent(String name, String lastname, String dni) {
        Student newstu = new Student(name,lastname,dni);
        logger.info(newstu.getName() + " " + newstu.getLastname() + " ha sido dado de alta");
        this.allstudents.put(dni,newstu);
    }

    @Override
    public void deleteStudent(String dni) {
        Student dstu = this.allstudents.get(dni);
        this.allstudents.remove(dni);
        logger.info(dstu.getName() + " " + dstu.getLastname() + " ha sido dado de baja");
    }

    @Override
    public void enrollStudent(String dni, String subject){
        Student stu = this.allstudents.get(dni);
        stu.enroll(subject);
        this.allstudents.put(dni,stu);
        logger.info(stu.getName() + " " + stu.getLastname() + " ha sido matriculado en " + subject);
    }

    @Override
    public List<String> getSubjectsStudent(String dni) {
        Student stu = this.allstudents.get(dni);
        return stu.getSubjects();
    }

    @Override
    public void removeSubject(String dni, String subject) {

    }

    @Override
    public Student getStudent(String dni) {
        return this.allstudents.get(dni);
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentslist = new LinkedList<Student>(this.allstudents.values());
        return studentslist;
    }

    @Override
    public int getNumStudents() {
        return this.allstudents.size();
    }
}
