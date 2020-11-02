package org.acme.resteasy.services;

import org.acme.resteasy.Exceptions.NoDataFoundException;
import org.acme.resteasy.models.Class;
import org.acme.resteasy.models.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {

    private static Map<Long, Student> students = new HashMap();

    public StudentService()
    {
        students.put(1L,new Student(1,"Tara Rosario","2015","Computer Engineering"));
        students.put(2L,new Student(2,"Taim Boyce","2015","Computer Engineering"));
        students.put(3L,new Student(3,"Joud Milner","2017","Computer Engineering"));
        students.put(4L,new Student(4,"Myra Adams","2018","Computer Engineering"));
        students.put(5L,new Student(5,"Liam Ian","2015","Management"));
        students.put(6L,new Student(6,"Dione Price","2015","Management"));
        students.put(7L,new Student(7,"Kasey East","2019","Management"));
        students.put(8L,new Student(8,"Annie Velez","2019","Management"));
        students.put(9L,new Student(9,"Stevie Richard","2015","Mechatronics"));
        students.put(10L,new Student(10,"Charley Stott","2015","Mechatronics"));
        students.put(11L,new Student(11,"Stacey Gould","2017","Mechatronics"));
        students.put(12L,new Student(12,"Noor Alvarez","2020","Mechatronics"));
        students.put(13L,new Student(13,"Dave Peter","2020","Applied Arts"));
        students.put(14L,new Student(14,"Laura Foy","2020","Accounting"));

    }

    public List<Student> getAllStudents()
    {

        return new ArrayList<Student>(students.values());
    }

    public Student getStudent(long id)
    {
        Student student = students.get(id);
        if(student==null)
            throw new NoDataFoundException("There is no student with the following id "+id+" in our database, please" +
                    " reenter the id of the student you're looking for");
        return student;
    }

    public Student addStudent(Student student)
    {
        student.setId(students.size()+1);
        students.put(student.getId(),student);
        return student;
    }

    public boolean idExists(long id)
    {
        for(Student student: students.values())
        {
           if(student.getId()==id)
               return true;
        }
        return false;
    }

    public Student updateStudent(Student student)
    {
        if(student.getId()<=0||student==null||!(idExists(student.getId())))
        {
            throw new NoDataFoundException("There is no such student in our database, please" +
                    " retry again.");
        }
        students.put(student.getId(), student);
        return student;
    }
    public Student removeStudent(long id)
    {
        if(!(idExists(id)))
        {
            throw new NoDataFoundException("There is no such student with the following id "+ id +
                    " in our database, please" +
                    " retry entering a valid student id.");
        }
        return students.remove(id);
    }

    public List<Student> getAllStudentsFromYear(int year)
    {
        if(year>2020)
        {
            throw new NoDataFoundException("The maximum year you can filter by is 2020.");
        }
        List<Student> studentsFilteredByYear= new ArrayList<>();
        for(Student student: students.values())
        {
            if(Integer.parseInt(student.getEnrollmentYear())==(year))
                studentsFilteredByYear.add(student);
        }
        return studentsFilteredByYear;
    }

    public List<Student> getAllStudentsFromYearRange(int startYear, int endYear)
    {
        if(endYear>2020)
        {
            throw new NoDataFoundException("The maximum year you can filter by is 2020.");
        }
        if(startYear<1900)
        {
            throw new NoDataFoundException("The minimum year you can filter by is 1900.");
        }
        List<Student> studentsFilteredByYear= new ArrayList<>();
        for(Student student: students.values())
        {
            int studentYear=Integer.parseInt(student.getEnrollmentYear());
            if(studentYear>=startYear && studentYear<=endYear)
                studentsFilteredByYear.add(student);
        }
        return studentsFilteredByYear;
    }

    public List<Student> getStudentsPaginated(int start, int size)
    {
        ArrayList<Student> studentsPaginated = new ArrayList<Student>(students.values());
        if(start+size>studentsPaginated.size()) return new ArrayList<Student>();
        return studentsPaginated.subList(start,start+size);

    }
}
