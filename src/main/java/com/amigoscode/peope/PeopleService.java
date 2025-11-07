package com.amigoscode.peope;
import com.amigoscode.dto.PeopleResponse;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;


@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PeopleDtoMapper peopleDtoMapper;

    public PeopleService(PeopleRepository peopleRepository, PeopleDtoMapper peopleDtoMapper) {
        this.peopleRepository = peopleRepository;
        this.peopleDtoMapper = peopleDtoMapper;
    }



    public List<PeopleResponse> getPeople(SortingOrder sortingOrder) {
        List<People> peopleList = peopleRepository.findAll();
        Stream<People> peopleStream = Stream.empty();
        if (sortingOrder == SortingOrder.DESC) {
            peopleStream = peopleList.stream().sorted((p1, p2) -> p2.getAge() - p1.getAge());
        } else {
            peopleStream = peopleList.stream().sorted((p1, p2) -> p1.getAge() - p2.getAge());
        }
        return peopleStream.map(peopleDtoMapper).toList();
    }
    
    public void insertPeople(List<People> peopleList) {
        if (peopleList == null || peopleList.isEmpty()) {
            return;
        }
        peopleRepository.saveAll(peopleList);
    }

    public void updatePeople(People people) {
        int id = people.getId();
        peopleRepository.findById(id).ifPresent(peopleToUpdate -> {
            peopleToUpdate.setName(people.getName());
            peopleToUpdate.setAge(people.getAge());
            peopleToUpdate.setGender(people.getGender());
            peopleRepository.save(peopleToUpdate);
        });
    }

}


