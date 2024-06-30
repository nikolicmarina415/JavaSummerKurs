package com.logate.summer.Controllers;

import com.logate.summer.Services.UserService;
import com.logate.summer.dto.user.UserDTO;
import com.logate.summer.dto.user.UserDTONoID;
import com.logate.summer.filter.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    //Servis za listu svih User-a
//    @RequestMapping(value="/api/users", method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> userDTOList = userService.getAll();
        return new ResponseEntity<List<UserDTO>>(userDTOList, HttpStatus.OK);
    }

    //Servis za user sa id-ijem
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable("id") Integer id) {
        UserDTO userDTO = userService.getById(id);
        if(userDTO == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    //Servus za user sa gradom
    //@RequestParam(value = "grad", required = true)
    @GetMapping("byCity")
    public ResponseEntity<List<UserDTO>> getByCity(@RequestParam(value = "grad") String city,
                                                   @RequestParam("godine") Integer age) {
        List<UserDTO> userDTOList = userService.getByCity(city);
        if(userDTOList.isEmpty()) {
            return new ResponseEntity<List<UserDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<UserDTO>>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("byReqMap")
    public ResponseEntity<List<UserDTO>> getByReqMap(@RequestParam Map<String, Object> mapa) {
        LOGGER.info("List mapiranih query param: {}", mapa);
        List<UserDTO> userDTOList = userService.getByReqMap(mapa);
        if(userDTOList.isEmpty()) {
            return new ResponseEntity<List<UserDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<UserDTO>>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("byClass")
    public ResponseEntity<List<UserDTO>> getByClass(UserFilter userFilter) {
        LOGGER.info("List mapiranih query param putem filter klase: {}", userFilter);
        List<UserDTO> userDTOList = userService.getByClass(userFilter);
        if(userDTOList.isEmpty()) {
            return new ResponseEntity<List<UserDTO>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<UserDTO>>(userDTOList, HttpStatus.OK);
    }

    //kreiranje novog korisnika
    //1. return UserDTO
    //2. return Integer id
    //3. return Void
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTONoID userDTONoID) {
        LOGGER.info("Predate req body {}", userDTONoID.toString());
        UserDTO userDTO = userService.create(userDTONoID);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
    }

    //update korisnika sa id-ijem
    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTONoID userDTONoID, @PathVariable Integer id) {
        LOGGER.info("Predate req body {}", userDTONoID.toString());
        UserDTO userDTO = userService.update(userDTONoID, id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //brisanje korisnika
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        LOGGER.info("Predata path variabla {}", id);
        userService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //requset headers
    @GetMapping("request-headers")
    public ResponseEntity<List<UserDTO>> getAllWithHeaders(@RequestHeader("DOCTYPE") String doctype) {
        LOGGER.info("Predati header-i: {}", doctype);
        return getAll();
    }

    @GetMapping("multiple-req-headers")
    public ResponseEntity<List<UserDTO>> getAllWithMultipleHeaders(@RequestHeader HttpHeaders httpHeaders) {
        LOGGER.info("Predati header-i: {}", httpHeaders);
//        httpHeaders.get("DOCTYPE");
        return getAll();
    }

    @GetMapping("return-headers")
    public ResponseEntity<List<UserDTO>> getAllWithReturnHeader() {
        List<UserDTO> userDTOList= userService.getAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("DOCTYPE", "tatata");
        httpHeaders.add("CLIENTDOC", "dsdsds");
        return new ResponseEntity<List<UserDTO>>(userDTOList, httpHeaders, HttpStatus.OK);
    }

}