package com.amigoscode.peope;

import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<People> getPeople() {
        return peopleRepository.findAll();
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
