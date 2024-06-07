package com.logate.summer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SummerApplication.class)
@ActiveProfiles("prod")
class FakeStoreTest {

    @Autowired
    FakeStore fakeStore;

    @Test
    public void configValues() {
        System.out.println(fakeStore.toString());
    }

    @Test
    public void configValuesProperties() {
        fakeStore.fakeStoreConfigValue();
    }

    @Test
    public void configValuesPropertiesEnv() {
        fakeStore.fakeStoreConfigValueEnv();
    }

}