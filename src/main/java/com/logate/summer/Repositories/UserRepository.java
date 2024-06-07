package com.logate.summer.Repositories;

import com.logate.summer.dto.UserDTO;
import com.logate.summer.dto.UserDTONoID;
import com.logate.summer.filter.UserFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    public List<UserDTO> getAll() {
        UserDTO user1 = new UserDTO();
        user1.setId(1);
        user1.setName("Marko Markovic");
        user1.setCity("Podgorica");
        user1.setAddress("Piperska bb");
        user1.setAge(25);

        UserDTO user2 = new UserDTO();
        user2.setId(2);
        user2.setName("Janko Jankovic");
        user2.setCity("Cetinje");
        user2.setAddress("4. Jul");
        user2.setAge(48);

        List<UserDTO> userDTOList =  new ArrayList<>();
        userDTOList.add(user1);
        userDTOList.add(user2);

        return userDTOList;
    }

    public UserDTO getById(Integer id) {
        List<UserDTO> userDTOList = getAll();
        for(UserDTO userDTO : userDTOList) {
            if(userDTO.getId().equals(id)) {
                return userDTO;
            }
        }
        return null;
    }

    public List<UserDTO> getByCity(String city) {
        List<UserDTO> userDTOList = getAll();
        List<UserDTO> userByCity = new ArrayList<>();
        for(UserDTO userDTO : userDTOList) {
            if(userDTO.getCity().equals(city)) {
                userByCity.add(userDTO);
            }
        }
        return userByCity;
    }

    public List<UserDTO> getByReqMap(Map<String, Object> mapa) {
        String city = (String) mapa.get("grad");
        return getByCity(city);
    }

    public List<UserDTO> getByClass(UserFilter userFilter) {
        return getByCity(userFilter.getCity());
    }

    public UserDTO create(UserDTONoID userDTONoID) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(50);
        userDTO.setName(userDTONoID.getName());
        userDTO.setAge(userDTONoID.getAge());
        userDTO.setAddress(userDTONoID.getAddress());
        userDTO.setCity(userDTONoID.getCity());
        return userDTO;
    }

    public UserDTO update(UserDTONoID userDTONoID, Integer id) {
        return new UserDTO();
    }

    public void delete(Integer id) {
        // logika za brisanje korinsika po id-iju iz baze
        UserDTO userDTO = getById(id);
    }
}
