package com.jm;


import com.jm.model.Student;
import io.smallrye.common.annotation.Blocking;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/stduent")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Blocking
public class GreetingResource {

    private StudentService studentService;

    public GreetingResource(StudentService studentService) {
        this.studentService = studentService;
    }


    @GET
    public List<Student> getStduent() {
        return studentService.findAll();

    }

    @POST
    public String addStudent(Student student){
        try {
            studentService.add(student);
            return "added student";
        }catch (Exception e){
            return "failed to add student";
        }
    }


}