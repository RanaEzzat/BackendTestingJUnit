package org.acme.resteasy.resourcesinterfaces;

import org.acme.resteasy.filters.ClassFilterBean;
import org.acme.resteasy.filters.StudentFilterBean;
import org.acme.resteasy.models.Class;
import org.acme.resteasy.models.Student;

import javax.ws.rs.*;
import java.util.List;

public interface ClassInterface {
    @GET
    public List<Class> getClasses();

    @GET
    @Path("/{classId}")
    public Class test(@PathParam("classId") long classId);

    @POST
    public Class addClass(Class c);

    @PUT
    @Path("/{classId}")
    public Class updateClass(@PathParam("classId") long id,Class c);

    @DELETE
    @Path("/{classId}")
    public void deleteStudent(@PathParam("classId") long id);

    @GET
    public List<Class> getClasses(@BeanParam ClassFilterBean filterBean);

}


