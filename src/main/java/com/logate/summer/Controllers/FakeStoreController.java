package com.logate.summer.Controllers;

import com.logate.summer.Services.FakeStoreService;
import com.logate.summer.dto.FakeStoreProductDTO;
import com.logate.summer.dto.FakeStoreProductDTONoID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
}
