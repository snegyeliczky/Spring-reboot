package com.amigoscode.dto;

import com.amigoscode.peope.Gender;

public record PeopleSummaryDto(
        Integer id,
        String fullName,
        int age,
        Gender gender
) {}
