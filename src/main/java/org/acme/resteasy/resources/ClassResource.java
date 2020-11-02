package org.acme.resteasy.resources;

import org.acme.resteasy.filters.ClassFilterBean;
import org.acme.resteasy.filters.StudentFilterBean;
import org.acme.resteasy.models.Class;
import org.acme.resteasy.models.Student;
import org.acme.resteasy.resourcesinterfaces.ClassInterface;
import org.acme.resteasy.services.ClassService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/classes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClassResource implements ClassInterface {

    ClassService classService = new ClassService();

    public List<Class> getClasses() {

        return classService.getAllClasses();
    }


    public Class test(@PathParam("classId") long classId)
    {
        return classService.getClass(classId);
    }

    public Class addClass(Class c)
    {
        return classService.addClass(c);
    }

    public Class updateClass(@PathParam("classId") long id,Class c)
    {
        c.setId(id);
        return classService.updateClass(c);
    }

    public void deleteStudent(@PathParam("classId") long id)
    {
        classService.removeClass(id);
    }


    public List<Class> getClasses(@BeanParam ClassFilterBean filterBean) {


        if(filterBean.getStart()>=0 && filterBean.getSize()>0)
        {
            return classService.getClassesPaginated(filterBean.getStart(),filterBean.getSize());
        }


        if(filterBean.getFirstLetter()!=0)
        {
            return classService.getAllClassesWithStartLetter(filterBean.getFirstLetter());
        }
        return classService.getAllClasses();
    }
}
