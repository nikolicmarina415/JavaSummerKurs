package com.logate.summer.Services;

import com.logate.summer.dto.FakeStoreProductDTO;
import com.logate.summer.dto.FakeStoreProductDTONoID;
import com.logate.summer.dto.FakeStoreUsersDTO;
import com.logate.summer.dto.FakeStoreUsersNoID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class FakeStoreService {

    @Value("${fakestore.url}")
    String url;

    @Autowired
    RestTemplate restTemplate;

    public List<FakeStoreProductDTO> getAllProducts() {
        String url = this.url+"/products";

        ParameterizedTypeReference<List<FakeStoreProductDTO>> listFakeStoreProductDTO = new ParameterizedTypeReference<List<FakeStoreProductDTO>>() {};
        ResponseEntity<List<FakeStoreProductDTO>> fakeStoreProductDTOResponseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, listFakeStoreProductDTO);

        return fakeStoreProductDTOResponseEntity.getBody();

    }

    public FakeStoreProductDTO getProductById(Integer id) {

        String url = this.url+"/products"+id;

        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, FakeStoreProductDTO.class);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        log.info("vrijednosti product: {}", fakeStoreProductDTO);
        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO createProduct(FakeStoreProductDTONoID fakeStoreProductDTONoID) {

        String url = this.url+"/products";

        HttpEntity<FakeStoreProductDTONoID> fakeStoreProductDTOHttpEntity = new HttpEntity<>(fakeStoreProductDTONoID);

        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
                restTemplate.exchange(url,HttpMethod.POST,fakeStoreProductDTOHttpEntity,FakeStoreProductDTO.class);
        return fakeStoreProductDTOResponseEntity.getBody();
    }
    public List<FakeStoreUsersDTO> getAllUsers() {
        String url = this.url+"/users";


        ParameterizedTypeReference<List<FakeStoreUsersDTO>> listFakeStoreUsersDTO =
                new ParameterizedTypeReference<List<FakeStoreUsersDTO>>(){};
        ResponseEntity<List<FakeStoreUsersDTO>> fakeStoreUsersDTOResponseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, listFakeStoreUsersDTO);

        return fakeStoreUsersDTOResponseEntity.getBody();
    }

    public FakeStoreUsersDTO getUsersById(Integer id) {
        String url = this.url+"/users"+id;

        ResponseEntity<FakeStoreUsersDTO> fakeStoreUsersDTOResponseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, FakeStoreUsersDTO.class);
        FakeStoreUsersDTO fakeStoreUsersDTO = fakeStoreUsersDTOResponseEntity.getBody();
        return fakeStoreUsersDTO;
    }

    public FakeStoreUsersDTO addUser(FakeStoreUsersNoID fakeStoreUsersDTONoID) {
        String url = this.url+"/users";

        HttpEntity<FakeStoreUsersNoID> fakeStoreUsersDTOHttpEntity = new HttpEntity<>(fakeStoreUsersDTONoID);

        ResponseEntity<FakeStoreUsersDTO> fakeStoreUsersDTOResponseEntity =
                restTemplate.exchange(url,HttpMethod.POST,fakeStoreUsersDTOHttpEntity,FakeStoreUsersDTO.class);
        return fakeStoreUsersDTOResponseEntity.getBody();
    }
}
}
