package com.logate.summer.Controllers;

import com.logate.summer.Services.CategoryService;
import com.logate.summer.dto.category.command.CategoryCommandDTO;
import com.logate.summer.dto.category.query.CategoryQueryDTO;
import com.logate.summer.dto.category.query.CategoryQueryDTOWithID;
import com.logate.summer.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //izlistavanje Categorija po nekim param
    @GetMapping
    public ResponseEntity<List<CategoryQueryDTOWithID>> findAllBySomething(@RequestBody CategoryQueryDTO categoryQueryDTO){
        return new ResponseEntity<>(categoryService.findAllBySomething(categoryQueryDTO), HttpStatus.OK);
    }

    @GetMapping("{id}")
    //naci category sa id=? vrati polja name, desc
    public ResponseEntity<CategoryQueryDTO> findById(@PathVariable("id") Integer id) {
        CategoryQueryDTO categoryQueryDTO = categoryService.findById(id);
        return new ResponseEntity<>(categoryQueryDTO, HttpStatus.OK);
    }

    //kreiranje nove kategorije
    @PostMapping
    public ResponseEntity<CategoryQueryDTOWithID> create(@RequestBody CategoryCommandDTO categoryCommandDTO) {
        return new ResponseEntity<CategoryQueryDTOWithID>(categoryService.create(categoryCommandDTO), HttpStatus.CREATED);
    }

}