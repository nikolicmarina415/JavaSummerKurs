package com.logate.summer.Services;

import com.logate.summer.Repositories.UserRepository;
import com.logate.summer.dto.UserDTO;
import com.logate.summer.dto.UserDTONoID;
import com.logate.summer.filter.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service //@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserDTO> getAll() {
        return userRepository.getAll();
    }

    public UserDTO getById(Integer id) {
        return userRepository.getById(id);
    }

    public List<UserDTO> getByCity(String city) {
        return userRepository.getByCity(city);
    }

    public List<UserDTO> getByReqMap(Map<String, Object> mapa) {
        return userRepository.getByReqMap(mapa);
    }

    public List<UserDTO> getByClass(UserFilter userFilter) {
        return userRepository.getByClass(userFilter);
    }

    public UserDTO create(UserDTONoID userDTONoID) {
        return userRepository.create(userDTONoID);
    }

    public UserDTO update(UserDTONoID userDTONoID, Integer id) {
        return userRepository.update(userDTONoID, id);
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
