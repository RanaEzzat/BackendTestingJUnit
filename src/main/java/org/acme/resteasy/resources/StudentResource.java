package org.acme.resteasy.resources;

import org.acme.resteasy.models.Student;
import org.acme.resteasy.filters.StudentFilterBean;
import org.acme.resteasy.resourcesinterfaces.StudentInterface;
import org.acme.resteasy.services.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource implements StudentInterface {

    StudentService studentService = new StudentService();


    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }


    public Student test(@PathParam("studentId") long studentId) {
        return studentService.getStudent(studentId);
        //return "Got the path parameter: "+studentId;
    }

    public Response addStudent(Student student, @Context UriInfo uriInfo) {
        Student newStudent = studentService.addStudent(student);
        String newId = String.valueOf(newStudent.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri)
                .entity(newStudent)
                .build();
    }

    public Student updateStudent(@PathParam("studentId") long id,Student student)
    {
        student.setId(id);
        return studentService.updateStudent(student);
    }

    public void deleteStudent(@PathParam("studentId") long id)
    {
        studentService.removeStudent(id);
    }

    public List<Student> getStudents(@BeanParam StudentFilterBean filterBean) {
        if(filterBean.getYear()>0)
        {
            return studentService.getAllStudentsFromYear(filterBean.getYear());
        }

        if(filterBean.getStart()>=0 && filterBean.getSize()>0)
        {
            return studentService.getStudentsPaginated(filterBean.getStart(),filterBean.getSize());
        }

        if(filterBean.getStartYear()>0 && filterBean.getEndYear()>0)
        {
            return studentService.getAllStudentsFromYearRange(filterBean.getStartYear(),filterBean.getEndYear());
        }
        return studentService.getAllStudents();
    }
}
