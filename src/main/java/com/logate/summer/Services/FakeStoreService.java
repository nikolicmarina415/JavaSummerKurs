package com.logate.summer.Services;

import com.logate.summer.dto.FakeStoreProductDTO;
import com.logate.summer.dto.FakeStoreProductDTONoID;
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
}
