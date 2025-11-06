package com.amigoscode.dto;

public record SoftwareEngineerResponse(
        Integer id,
        String nickName,
        String techStack,
        PeopleSummaryDto people
) {}
