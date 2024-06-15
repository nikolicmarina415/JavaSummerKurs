package com.logate.summer.Services;

import com.logate.summer.dto.fakestore.FakeStoreProductDTO;
import com.logate.summer.dto.fakestore.FakeStoreProductDTONoID;
import com.logate.summer.dto.fakestore.CategoryFakeDTO;
import com.logate.summer.dto.fakestore.UserFakeDTO;
import com.logate.summer.dto.fakestore.UserFakeDTONoID;
import lombok.extern.slf4j.Slf4j;
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

    public List<UserFakeDTO> getAllUsers() {

        String url = this.url+"/users";
        ParameterizedTypeReference<List<UserFakeDTO>> parameterizedTypeReference = new ParameterizedTypeReference<List<UserFakeDTO>>() {};
        try {
            return restTemplate.exchange(url,HttpMethod.GET,null,parameterizedTypeReference)
                    .getBody();
        }catch (Exception e) {
            log.error("Dogodila se greska {}:", e.getMessage());
            return null;
        }
    }

    public List<CategoryFakeDTO> getAllCategories() {
        String url = this.url + "/products/categories";
        ParameterizedTypeReference<List<CategoryFakeDTO>> parameterizedTypeReference = new ParameterizedTypeReference<List<CategoryFakeDTO>>() {};
        try {
            return restTemplate.exchange(url,HttpMethod.GET,null,parameterizedTypeReference)
                    .getBody();
        }catch (Exception e) {
            log.error("Dogodila se greska {}:", e.getMessage());
            return null;
        }
    }

    public UserFakeDTO getUserById(Integer id) {
        String url = this.url + "/users" + id;
        try {
            return restTemplate.exchange(url,HttpMethod.GET,null,UserFakeDTO.class)
                    .getBody();
        }catch (Exception e) {
            log.error("Dogodila se greska {}:", e.getMessage());
            return null;
        }
    }

    public UserFakeDTO createUser(UserFakeDTONoID userFakeDTONoID) {
        String url = this.url + "/users";
        HttpEntity<UserFakeDTONoID> httpEntity = new HttpEntity<UserFakeDTONoID>(userFakeDTONoID);
        log.info("Request object:{}", httpEntity);
        try {
            return restTemplate.exchange(url,HttpMethod.POST,httpEntity,UserFakeDTO.class)
                    .getBody();
        }catch (Exception e) {
            log.error("Dogodila se greska {}:", e.getMessage());
            return null;
        }
    }
}