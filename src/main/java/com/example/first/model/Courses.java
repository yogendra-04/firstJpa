package com.example.first.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Courses {
    @Id
    private  int  cId;
    private String cName;
    @OneToOne(mappedBy = "Students")
    private Students student;


    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public Courses(int cId, String cName) {
        this.cId = cId;
        this.cName = cName;
    }
    public Courses(){}

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }



    @Override
    public String toString() {
        return "Courses{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                '}';
    }
}
