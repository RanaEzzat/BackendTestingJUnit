package org.acme.resteasy.services;

import org.acme.resteasy.Exceptions.NoDataFoundException;
import org.acme.resteasy.models.Class;
import org.acme.resteasy.models.DatabaseClass;
import org.acme.resteasy.Exceptions.ErrorMessage;
import org.acme.resteasy.models.Student;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassService {
    private static Map<Long, Class> classes = DatabaseClass.getClasses();

    //Assigning each student their class by checking their major and enrollment year
    public static int StudentsClass(Student student)
    {

        if(student.getMajor().equals("Computer Engineering"))
        {
            if(student.getEnrollmentYear().equals("2015"))
                return 0;
            return 1;

        }

        if(student.getMajor().equals("Management"))
        {
            if(student.getEnrollmentYear().equals("2015"))
                return 2;
            return 3;
        }

        if(student.getMajor().equals("Mechatronics"))
        {

            if(student.getEnrollmentYear().equals("2015"))
                return 4;
            return 5;
        }

        return 6;
    }

    //Creating classes
    public ClassService()
    {
        StudentService studentService = new StudentService();
        //Creating a list which will contain 7 lists of students
        //for each of the 7 classes
        List<List<Student>> ClassesList = new ArrayList<List<Student>>();


        for(int i=0;i<7;i++)
        {
            ClassesList.add(new ArrayList());
        }

        //Filling the 7 classes with students using the StudentsClass method which
        //assigns each student to a class based on their major and enrollmentYear
        for(int i=1;i<studentService.getAllStudents().size()+1;i++)
        {
            Student student = studentService.getStudent(i);
            int assignedClass = StudentsClass(student);
            List<Student> studentsInClass=ClassesList.remove(assignedClass);
            studentsInClass.add(student);
            ClassesList.add(assignedClass,studentsInClass);
        }

        //Creating classes
        Class class1 = new Class(1,"Theory of Computation",ClassesList.get(0));
        Class class2 = new Class(2,"Elective: Theory of Computation",ClassesList.get(1));
        Class class3 = new Class(3,"ITPM",ClassesList.get(2));
        Class class4 = new Class(4,"Elective: ITPM",ClassesList.get(3));
        Class class5 = new Class(5,"Computer Organization",ClassesList.get(4));
        Class class6 = new Class(6,"Elective: Computer Organization",ClassesList.get(5));
        Class class7 = new Class(7,"Elective-All Majors: Art",ClassesList.get(6));


        classes.put(1L,class1);
        classes.put(2L,class2);
        classes.put(3L,class3);
        classes.put(4L,class4);
        classes.put(5L,class5);
        classes.put(6L,class6);
        classes.put(7L,class7);
        }



    public List<Class> getAllClasses()
    {
        return new ArrayList<Class>(classes.values());
    }
    public Class getClass(long id)
    {
        ErrorMessage errorMessage = new ErrorMessage("There is no class in our database with " +
                "the following id: "+id+".",404,"http://localhost:8080");
        Response response = Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
        Class c = classes.get(id);
        if(c==null)
        {
            throw new WebApplicationException(response);
        }
        return c;
    }

    public Class addClass(Class c)
    {
        c.setId(classes.size()+1);
        classes.put(c.getId(),c);
        return c;
    }

    public boolean idExists(long id)
    {
        for(Class c: classes.values())
        {
            if(c.getId()==id)
                return true;
        }
        return false;
    }

    public Class updateClass(Class c)
    {
        if(c.getId()<=0||(!(idExists(c.getId()))))
        {
            throw new NoDataFoundException("There is no such class in our database, please" +
                    " retry again.");
        }
        classes.put(c.getId(), c);
        return c;
    }

    public Class removeClass(long id)
    {
        if(id<=0||(!(idExists(id))))
        {
            throw new NoDataFoundException("There is no class in our database with the following id "+id+" , please" +
                    " retry again.");
        }
        return classes.remove(id);
    }

    public List<Class> getAllClassesWithStartLetter(char firstLetter)
    {
        if(!((firstLetter>='a'&&firstLetter<='z')||
                (firstLetter>='A'&&firstLetter<='Z')))
        {
            throw new NoDataFoundException("The first letter that you entered to filter by: "+firstLetter+" is not a valid letter," +
                    " please retry again.");
        }
        List<Class> classesFilteredByLetter= new ArrayList<>();
        //Making the filtered letter a capital letter in case it is lowercase
        String sFirstLetter=""+firstLetter;
        sFirstLetter=sFirstLetter.toUpperCase();


        for(Class c: classes.values())
        {
            String sClassFirstLetter=""+c.getName().charAt(0);
            if(sClassFirstLetter.equals(sFirstLetter))
                classesFilteredByLetter.add(c);
        }
        return classesFilteredByLetter;
    }
    public List<Class> getClassesPaginated(int start, int size)
    {
        ArrayList<Class> classesPaginated = new ArrayList<Class>(classes.values());
        if(start+size>classesPaginated.size()) return new ArrayList<Class>();
        return classesPaginated.subList(start,start+size);

    }
}
