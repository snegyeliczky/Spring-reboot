package com.amigoscode.dto;

import com.amigoscode.peope.Gender;
import java.util.List;

public record PeopleResponse(
        Integer id,
        String name,
        int age,
        Gender gender,
        SoftwareEngineerSummaryDto softwareEngineer,
        List<BookSummaryDto> books
) {}
