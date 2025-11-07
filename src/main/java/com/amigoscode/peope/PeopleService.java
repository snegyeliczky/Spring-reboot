package com.amigoscode.peope;

import com.amigoscode.dto.BookSummaryDto;
import com.amigoscode.dto.PeopleResponse;
import com.amigoscode.dto.SoftwareEngineerSummaryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;


@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    private static PeopleResponse toDto(People p) {
        var se = p.getSoftwareEngineer();
        var seDto = se == null ? null : new SoftwareEngineerSummaryDto(
                se.getId(), se.getNickName(), se.getTechStack()
        );
        var bookDtos = p.getBooks() == null ? List.<BookSummaryDto>of()
                : p.getBooks().stream()
                .map(b -> new BookSummaryDto(b.getName(), b.getAuthor()))
                .toList();
        return new PeopleResponse(
                p.getId(), p.getName(), p.getAge(), p.getGender(), seDto, bookDtos
        );
    }

    public List<PeopleResponse> getPeople(SortingOrder sortingOrder) {
        List<People> peopleList = peopleRepository.findAll();
        Stream<People> peopleStream = Stream.empty();
        if (sortingOrder == SortingOrder.DESC) {
            peopleStream = peopleList.stream().sorted((p1, p2) -> p2.getAge() - p1.getAge());
        } else {
            peopleStream = peopleList.stream().sorted((p1, p2) -> p1.getAge() - p2.getAge());
        }
        return peopleStream.map(PeopleService::toDto).toList();
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


