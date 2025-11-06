package com.amigoscode;

import com.amigoscode.peope.People;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class SoftwareEngineer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickName;
    private String techStack;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn( referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "software_engineer_people_id_fk"),
            nullable = false,
            unique = true
    )
    @JsonBackReference
    private People people;


    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public SoftwareEngineer() {
    }

    public SoftwareEngineer(Integer id, String nickName, String techStack) {
        this.id = id;
        this.nickName = nickName;
        this.techStack = techStack;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareEngineer that = (SoftwareEngineer) o;
        return Objects.equals(id, that.id) && Objects.equals(nickName, that.nickName) && Objects.equals(techStack, that.techStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, techStack);
    }
}
