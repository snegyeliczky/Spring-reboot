package com.amigoscode.peope;

import com.amigoscode.dto.BookSummaryDto;
import com.amigoscode.dto.PeopleResponse;
import com.amigoscode.dto.SoftwareEngineerSummaryDto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class PeopleDtoMapper implements Function<People, PeopleResponse> {


    public PeopleResponse apply(People people) {
        var se = people.getSoftwareEngineer();
        var seDto = se == null ? null : new SoftwareEngineerSummaryDto(
                se.getId(), se.getNickName(), se.getTechStack()
        );
        var bookDtos = people.getBooks() == null ? List.<BookSummaryDto>of()
                : people.getBooks().stream()
                .map(b -> new BookSummaryDto(b.getName(), b.getAuthor()))
                .toList();

        return new PeopleResponse(
                people.getId(),
                people.getName(),
                people.getAge(),
                people.getGender(),
                seDto,
                bookDtos
        );
    }
}
