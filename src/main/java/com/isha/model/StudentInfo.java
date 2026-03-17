package com.isha.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class StudentInfo {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically increment in the ids
    private Integer sid;
    @Column(name = "student_name")
    private String sName;
    @Column(name = "student_city")
    private String sCity;
    // large objects
    // image
    @Lob // large object annotation (no need to specify the type like blob or clob it will be done automatically)
    @Column(name = "student_image",length = 10000) // (name = "student_image",length = 100000) : length keeping large as the image size can be large
    private byte[] sImage;
    // large text
    @Lob
    @Column(name = "student_bio")
    private char[] sBio; // char are mutable so not preferred over String but here for learning we are using it


    // zero param constructor
    public StudentInfo() {
        System.out.println("zero param constructor of StudentInfo");
    }

    // setter and getters for lobs
    public byte[] getsImage()
    {
        return sImage;
    }
    public void setsImage(byte[] sImage)
    {
        this.sImage=sImage;
    }
    public char[] getsBio()
    {
        return sBio;
    }
    public void setsBio(char[] sBio)
    {
        this.sBio=sBio;
    }

    // getter setter
    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sName;
    }

    public void setSname(String sName) {
        this.sName = sName;
    }
    public String getScity() {
        return sCity;
    }

    public void setScity(String sCity) {
        this.sCity = sCity;
    }
/*
image = huge binary data
bio = large text
console will become messy / slow
so...not printing it on the console!!
 */
    @Override
    public String toString() {
        return "StudentInfo{" +
                "sid=" + sid +
                ", sName= " + sName +
                ", sCity= " + sCity +
                ", imageSize= " + (sImage != null ? sImage.length + " bytes" : "null") +
                ", bioLength= " + (sBio != null ? sBio.length + " chars" : "null") +
                '}';
    }
}
