package edu.upc.dsa.services;


import edu.upc.dsa.models.Student;
import edu.upc.dsa.utils.StudentManager;
import edu.upc.dsa.utils.StudentManagerImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/students", description = "Endpoint to Students Service")
@Path("/students")
public class StudentService {

    private StudentManager sm;

    public StudentService(){
        this.sm = StudentManagerImp.getInstance();
        if(sm.getNumStudents() == 0){
            this.sm.addStudent("Marc","Vila","43222633K");
            this.sm.addStudent("Toni","Garcia","543123453L");
            this.sm.addStudent("Paula","Tomas","76123451K");

            this.sm.enrollStudent("43222633K","DSA");
            this.sm.enrollStudent("43222633K","UAS");
            this.sm.enrollStudent("543123453L","DSA");
        }
    }


    @GET
    @ApiOperation(value = "get all students", notes = "Returns list of all studens")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Student.class, responseContainer="List"),
    })
    @Path("/Student")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents(){

        List<Student> allstudents = this.sm.getAllStudents();

        GenericEntity<List<Student>> entity = new GenericEntity<List<Student>>(allstudents) {};
        return Response.status(201).entity(entity).build() ;
    }

    @GET
    @ApiOperation(value = "get student by dni", notes = "We get a student based of a dni")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Student.class),
            @ApiResponse(code = 404, message = "Student not found")
    })
    @Path("Student/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("dni") String dni) {
        Student stu = this.sm.getStudent(dni);
        if (stu == null) {
            return Response.status(404).build();
        }
        else return Response.status(201).entity(stu).build();
    }

    @DELETE
    @ApiOperation(value = "delete a Student", notes = "Remove a student of database")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Student not found")
    })
    @Path("Student/{dni}")
    public Response deleteStudent(@PathParam("dni") String dni) {
        Student t = this.sm.getStudent(dni);
        if (t == null) return Response.status(404).build();
        else this.sm.deleteStudent(dni);
        return Response.status(201).build();
    }


    @POST
    @ApiOperation(value = "add new student", notes = "Adding new student to database")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Student.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/Student/{dni}/{name}/{lastname}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newStudent(@PathParam("dni") String dni, @PathParam("name") String name, @PathParam("lastname") String lastname) {

        if(dni == null || name == null || lastname == null){
            return Response.status(500).build();
        }
        this.sm.addStudent(name,lastname,dni);
        Student student = this.sm.getStudent(dni);
        return Response.status(201).entity(student).build();
    }


    @PUT
    @ApiOperation(value = "enroll a students in a class", notes = "student enrolles")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Subject/Student not found")
    })
    @Path("/Student/{dni}/{subject}")
    public Response enrollStudent(@PathParam("dni") String dni,@PathParam("subject") String subject) {

        Student stu = this.sm.getStudent(dni);

        if (stu == null) return Response.status(404).build();
        else {
            this.sm.enrollStudent(dni,subject);
            return Response.status(201).build();
        }
    }

    @GET
    @ApiOperation(value = "get students subjects", notes= "get list with the subjects a student is taking")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Subject/Student not found")
    })
    @Path("{dni}/Subjects")
    public Response getSubjectsStudent(@PathParam("dni") String dni) {
        Student stu = this.sm.getStudent(dni);

        if (stu == null) return Response.status(404).build();
        else {
            LinkedList<String> subjects = stu.getSubjects();
            GenericEntity<LinkedList<String>> res = new GenericEntity<LinkedList<String>>(subjects) {
            };
            return Response.status(201).entity(res).build();
        }
    }

}
