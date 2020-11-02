package org.acme.resteasy.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {

    private long id;
    private String Name;
    private String EnrollmentYear;
    private String Major;


    public Student()
    {

    }
    public Student(long id,String Name, String EnrollmentYear, String Major) {
        this.id=id;
        this.Name = Name;
        this.EnrollmentYear = EnrollmentYear;
        this.Major = Major;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEnrollmentYear() {
        return EnrollmentYear;
    }

    public void setEnrollmentYear(String EnrollmentYear) {
        this.EnrollmentYear = EnrollmentYear;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String Major) {
        this.Major = Major;
    }
}
