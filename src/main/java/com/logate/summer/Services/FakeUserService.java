package com.logate.summer.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FakeUserService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doSomethingFromFakeUser() {
    }
}