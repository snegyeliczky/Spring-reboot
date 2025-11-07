package com.amigoscode;

import com.amigoscode.book.Book;
import com.amigoscode.book.BookRepository;
import com.amigoscode.peope.Gender;
import com.amigoscode.peope.People;
import com.amigoscode.peope.PeopleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBasicsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(SpringBasicsApplication.class, args);
        String[] names = configurableApplicationContext.getBeanDefinitionNames();

        System.out.println("Number of beans loaded: " + names.length);
    }


    @Bean
    public String helloWorld() {
        return "Hello World";
    }

    @Bean
    CommandLineRunner commandLineRunner(UserService userService, PeopleRepository peopleRepository, SoftwareEngineerRepository softwareEngineerRepository, BookRepository bookRepository) {
        return args -> {
            // Seed only once to avoid duplicates on every restart
            if (peopleRepository.count() == 0 && softwareEngineerRepository.count() == 0) {
                SoftwareEngineer softwareEngineer = new SoftwareEngineer(null, "Putyesz", "Java");
                People people = new People("Tuluse", 1, Gender.MALE);
                people.setSoftwareEngineer(softwareEngineer);
                softwareEngineer.setPeople(people);
                Book book = new Book("name", "JKR");
                Set<Book> books = new HashSet<>();
                books.add(book);
                people.setBooks(books);
                book.setPeople(people);
                peopleRepository.save(people);
            }

            System.out.println("CommandLineRunner: " + userService.getUserName());
        };
    }


}
