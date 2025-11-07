package com.amigoscode.book;

import com.amigoscode.peope.People;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String author;
    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "book_people_id_fk"
            )

    )
    @JsonBackReference(value = "people-books")
    private People people;

    public Book() {}

    public Book(String name, String author) {
        this.name = name;
        this.author = author;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author);
    }
}
