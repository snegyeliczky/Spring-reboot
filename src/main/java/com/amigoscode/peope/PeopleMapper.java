package com.amigoscode.peope;

import com.amigoscode.dto.PeopleSummaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PeopleMapper {
    PeopleMapper INSTANCE = Mappers.getMapper(PeopleMapper.class);
    @Mapping(source = "name", target = "fullName")
    PeopleSummaryDto popleToPeopleSummaryDto(People people);
}
