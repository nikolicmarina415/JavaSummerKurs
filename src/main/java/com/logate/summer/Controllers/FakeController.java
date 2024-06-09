package com.logate.summer.Controllers;


import com.logate.summer.Services.FakeService;
import com.logate.summer.dto.FakeStoreProductDTO;
import com.logate.summer.dto.FakeStoreProductDTONoID;
import com.logate.summer.dto.FakeStoreUsersDTO;
import com.logate.summer.dto.FakeStoreUsersNoID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fakestore")
public class FakeController {

    @Autowired
    FakeService fakeService;

    @GetMapping("users")
    public ResponseEntity<List<FakeStoreUsersDTO>> getAllUsers() {
        List<FakeStoreUsersDTO> fakeStoreUsersDTOList = fakeService.getAllUsers();

        return new ResponseEntity<>(fakeStoreUsersDTOList, HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<FakeStoreUsersDTO> getUsersById(@PathVariable Integer id) {
        FakeStoreUsersDTO fakeStoreUsersDTO = fakeService.getUsersById(id);

        return new ResponseEntity<>(fakeStoreUsersDTO, HttpStatus.OK);
    }
    @PostMapping("users")
    public ResponseEntity<FakeStoreUsersDTO> addUser(@RequestBody FakeStoreUsersNoID fakeStoreUsersDTONoID) {
        FakeStoreUsersDTO fakeStoreUsersDTO = fakeService.addUser(fakeStoreUsersDTONoID);

        return new ResponseEntity<>(fakeStoreUsersDTO, HttpStatus.OK);
    }
}
