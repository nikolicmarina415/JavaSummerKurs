package com.logate.summer.Services;

import com.logate.summer.Repositories.CategoryRepository;
import com.logate.summer.dto.category.command.CategoryCommandDTO;
import com.logate.summer.dto.category.query.CategoryQueryDTO;
import com.logate.summer.dto.category.query.CategoryQueryDTOWithID;
import com.logate.summer.entities.Category;
import com.logate.summer.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
//@RequiredArgsConstructor
public class CategoryService {

    //    private final CategoryRepository categoryRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;


    public List<CategoryQueryDTOWithID> findAllBySomething(CategoryQueryDTO categoryQueryDTO) {
        String name = categoryQueryDTO.getName();
        List<Category> categoryList = categoryRepository.findAllBySomething(name);

//        List<CategoryQueryDTOWithID> categoryQueryDTOWithIDS = new ArrayList<>();
//
//        for(Category category : categoryList) {
//            CategoryQueryDTOWithID categoryQueryDTOWithID =  categoryMapper.CategoryToCategoryQueryDTOWithID(category);
//            categoryQueryDTOWithIDS.add(categoryQueryDTOWithID);
//        }
//
//        return categoryQueryDTOWithIDS;

        return categoryList.stream().map(categoryMapper::CategoryToCategoryQueryDTOWithID)
                .toList();
    }

    public CategoryQueryDTO findById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        return categoryMapper.CategoryToCategoryQueryDTO(category);
    }

    public CategoryQueryDTOWithID create(CategoryCommandDTO categoryCommandDTO) {
        Category category = categoryMapper.CategoryCommandDTOToCategory(categoryCommandDTO);
        categoryRepository.save(category);

        return categoryMapper.CategoryToCategoryQueryDTOWithID(category);
    }
}