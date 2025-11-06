package com.amigoscode.book;

import com.amigoscode.peope.People;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String author;
//    @ManyToOne(
//            cascade = CascadeType.MERGE
//    )
//    @JoinColumn(
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "book_people_id_fk"
//            ),
//            nullable = true,
//            unique = true
//    )
//    private People People;

    public Book() {}

    public Book(String name, int id, String author) {
        this.name = name;
        this.id = id;
        this.author = author;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
