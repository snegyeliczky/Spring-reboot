package com.amigoscode.peope;
import com.amigoscode.dto.PeopleResponse;

import com.amigoscode.dto.PeopleSummaryDto;
import org.apache.commons.collections4.IterableUtils;
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
        Iterable<People> peopleIterable = peopleRepository.findAll();
        List<People> people = IterableUtils.toList(peopleIterable);
        Stream<People> peopleStream;
        if (sortingOrder == SortingOrder.DESC) {
            peopleStream = people.stream().sorted((p1, p2) -> p2.getAge() - p1.getAge());
        } else {
            peopleStream = people.stream().sorted((p1, p2) -> p1.getAge() - p2.getAge());
        }
        return peopleStream.map(peopleDtoMapper).toList();

    }


    public PeopleSummaryDto getPeopleById(Integer id) {
        People people =  peopleRepository.findById(id).orElse(null);
        return PeopleMapper.INSTANCE.popleToPeopleSummaryDto(people);

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


