package com.amigoscode.peope;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/people")
public class PeopleController {


    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    public enum Gender {
        MALE,
        FEMALE
    }

    public record Person(
            int id,
            String name,
            int age,
            Gender gender
    ){}

    public static List<Person> people = new ArrayList<>();

    static {
        people.add(new Person(1, "Jhon", 20, Gender.MALE));
        people.add(new Person(2, "Marin", 30, Gender.FEMALE));
    }

    @GetMapping
    public List<People> getAllPeople(
            @RequestParam(
                    value = "sort",
                    required = false,
                    defaultValue = "ASC"
            ) SortingOrder sortingOrder
    ) {
         List<People> peoplelist =  peopleService.getPeople();
        if (Objects.requireNonNull(sortingOrder) == SortingOrder.DESC) {
            return peoplelist.stream().sorted((p1, p2) -> p2.getAge() - p1.getAge()).toList();
        }
        return peoplelist;
    }

    @PostMapping("/insertPeople")
    public void insertPeople(@RequestBody List<People> people) {
        peopleService.insertPeople(people);
    }

    @PutMapping("/updatePeople")
    public void updatePeople(@RequestBody People people) {
        peopleService.updatePeople(people);
    }

}
