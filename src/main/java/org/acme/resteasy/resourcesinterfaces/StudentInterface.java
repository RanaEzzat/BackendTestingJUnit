package org.acme.resteasy.resourcesinterfaces;

import org.acme.resteasy.filters.StudentFilterBean;
import org.acme.resteasy.models.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

public interface StudentInterface {
    @GET
    public List<Student> getStudents();


    @GET
    @Path("/{studentId}")
    public Student test(@PathParam("studentId") long studentId);

    @POST
    public Response addStudent(Student student, @Context UriInfo uriInfo);

    @PUT
    @Path("/{studentId}")
    public Student updateStudent(@PathParam("studentId") long id,Student student);

    @DELETE
    @Path("/{studentId}")
    public void deleteStudent(@PathParam("studentId") long id);

    @GET
    public List<Student> getStudents(@BeanParam StudentFilterBean filterBean);
}
