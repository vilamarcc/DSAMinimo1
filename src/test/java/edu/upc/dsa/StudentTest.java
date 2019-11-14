package edu.upc.dsa;

import edu.upc.dsa.utils.StudentManager;
import edu.upc.dsa.utils.StudentManagerImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;


public class StudentTest {
    StudentManager manager;

    @Before
    public void setUp(){
        manager = StudentManagerImp.getInstance();

        manager.addStudent("Marc","Vila","43222633K");
        manager.addStudent("Toni","Garcia","543123453L");
        manager.addStudent("Paula","Tomas","76123451K");

        manager.enrollStudent("43222633K","DSA");
        manager.enrollStudent("43222633K","UAS");
        manager.enrollStudent("543123453L","DSA");
    }

    @Test
    public void testDelete(){
        manager.deleteStudent("76123451K");
        Assert.assertEquals(2,manager.getNumStudents());
    }

    @Test
    public void testSubjects(){
        ArrayList<String> subjectsStudent= (ArrayList<String>) manager.getSubjectsStudent("43222633K");
        Assert.assertEquals("DSA",subjectsStudent.get(0));
        Assert.assertEquals("UAS",subjectsStudent.get(1));
        Assert.assertEquals(2,subjectsStudent.size());
    }
}
