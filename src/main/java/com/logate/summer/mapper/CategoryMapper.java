package com.logate.summer.mapper;

import com.logate.summer.dto.category.command.CategoryCommandDTO;
import com.logate.summer.dto.category.query.CategoryQueryDTO;
import com.logate.summer.dto.category.query.CategoryQueryDTOWithID;
import com.logate.summer.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(source = "description", target = "desc")
    CategoryQueryDTO CategoryToCategoryQueryDTO(Category category);


    @Mapping(source = "description", target = "desc")
    @Mapping(source = "id", target = "idetif")
    CategoryQueryDTOWithID CategoryToCategoryQueryDTOWithID(Category category);

    @Mapping(source = "desc", target = "description")
    Category CategoryCommandDTOToCategory(CategoryCommandDTO categoryCommandDTO);
}