package com.logate.summer.Controllers;

import com.logate.summer.Services.FakeStoreService;
import com.logate.summer.dto.fakestore.FakeStoreProductDTO;
import com.logate.summer.dto.fakestore.FakeStoreProductDTONoID;
import com.logate.summer.dto.fakestore.CategoryFakeDTO;
import com.logate.summer.dto.fakestore.UserFakeDTO;
import com.logate.summer.dto.fakestore.UserFakeDTONoID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fakestore")
public class FakeStoreController {

    @Autowired
    FakeStoreService fakeStoreService;

    //lista svih products sa FakeStore
    @GetMapping("products")
    public ResponseEntity<List<FakeStoreProductDTO>> getAllProducts() {
        List<FakeStoreProductDTO> fakeStoreProductDTOList = fakeStoreService.getAllProducts();

        return new ResponseEntity<>(fakeStoreProductDTOList, HttpStatus.OK);
    }

    //product sa id-ijem
    @GetMapping("products/{id}")
    public ResponseEntity<FakeStoreProductDTO> getProductById(@PathVariable Integer id) {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreService.getProductById(id);

        return new ResponseEntity<>(fakeStoreProductDTO, HttpStatus.OK);
    }

    @PostMapping("products")
    public ResponseEntity<FakeStoreProductDTO> createProduct(@RequestBody FakeStoreProductDTONoID fakeStoreProductDTONoID) {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreService.createProduct(fakeStoreProductDTONoID);

        return new ResponseEntity<>(fakeStoreProductDTO, HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<List<UserFakeDTO>> getAllUsers() {
        List<UserFakeDTO> userFakeDTOList = fakeStoreService.getAllUsers();
        if(userFakeDTOList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userFakeDTOList, HttpStatus.OK);
    }

    @GetMapping("products/categories")
    public ResponseEntity<List<CategoryFakeDTO>> getAllCategories() {
        List<CategoryFakeDTO> categoryFakeDTOList = fakeStoreService.getAllCategories();
        if(categoryFakeDTOList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryFakeDTOList, HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserFakeDTO> getUserById(@PathVariable Integer id) {
        UserFakeDTO userFakeDTO = fakeStoreService.getUserById(id);
        if(userFakeDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userFakeDTO, HttpStatus.OK);
    }

    @PostMapping("users")
    public ResponseEntity<UserFakeDTO> createUser(@RequestBody UserFakeDTONoID userFakeDTONoID) {
        UserFakeDTO userFakeDTO = fakeStoreService.createUser(userFakeDTONoID);
        if(userFakeDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userFakeDTO, HttpStatus.OK);
    }
}