package org.acme.resteasy.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Class {
    private long id;
    private String Name;
    private List<Student> Students;

    public Class() {
    }

    public Class(long id, String Name, List<Student> Students) {
        this.id = id;
        this.Name = Name;
        this.Students = Students;
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

    public List<Student> getStudents() {
        return Students;
    }

    public void setStudents() {
        this.Students=Students;
    }


}
