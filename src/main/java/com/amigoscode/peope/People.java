package com.amigoscode.peope;

import com.amigoscode.SoftwareEngineer;
import com.amigoscode.book.Book;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class People {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int age;
    private Gender gender;
    @OneToOne(mappedBy = "people", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private SoftwareEngineer softwareEngineer;

//    @OneToMany(
//            mappedBy = "People",
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//            orphanRemoval = true
//    )
//    private Set<Book> books = new HashSet<>();

    public People() {
    }

    public People(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public SoftwareEngineer getSoftwareEngineer() {
        return softwareEngineer;
    }

    public void setSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        this.softwareEngineer = softwareEngineer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return id == people.id && age == people.age && Objects.equals(name, people.name) && gender == people.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, gender);
    }
}
