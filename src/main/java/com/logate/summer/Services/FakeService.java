package com.logate.summer.Services;

import com.logate.summer.dto.FakeStoreUsersDTO;
import com.logate.summer.dto.FakeStoreUsersNoID;
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
public class FakeService {


    @Value("${fakestore.url}")
    String url;

    @Autowired
    RestTemplate restTemplate;


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
        Log.info("vrijednosti product: {}", fakeStoreUsersDTO);
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
