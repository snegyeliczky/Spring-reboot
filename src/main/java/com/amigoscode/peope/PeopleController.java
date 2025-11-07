package com.amigoscode.peope;

import com.amigoscode.dto.BookSummaryDto;
import com.amigoscode.dto.PeopleResponse;
import com.amigoscode.dto.PeopleSummaryDto;
import com.amigoscode.dto.SoftwareEngineerSummaryDto;
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
    public List<PeopleResponse> getAllPeople(
            @RequestParam(
                    value = "sort",
                    required = false,
                    defaultValue = "ASC"
            ) SortingOrder sortingOrder
    ) {
        return peopleService.getPeople(sortingOrder);
    }

    @GetMapping("/{id}")
    public PeopleSummaryDto getPeopleById(@PathVariable Integer id) {
        return peopleService.getPeopleById(id);
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
