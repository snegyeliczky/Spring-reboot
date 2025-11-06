package com.amigoscode.dto;

import com.amigoscode.peope.Gender;

public record PeopleSummaryDto(
        Integer id,
        String name,
        int age,
        Gender gender
) {}
