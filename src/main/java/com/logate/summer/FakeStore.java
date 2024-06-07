package com.logate.summer;

import com.logate.summer.configurations.FakeStoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class FakeStore {

    @Autowired
    Environment environment;

//    @Value("${fakestore.url}")
//    String url;
//    @Value("${fakestore.username}")
//    String username;
//    @Value("${fakestore.password}")
//    String password;
//    @Value("${fakestore.ttl}")
//    Integer ttl;
//
//    @Override
//    public String toString() {
//        return "FakeStore{" +
//                "url='" + url + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", ttl=" + ttl +
//                '}';
//    }

    @Autowired
    FakeStoreConfig fakeStoreConfig;

    public void fakeStoreConfigValue() {
        System.out.println("url:"+ fakeStoreConfig.getUrl());
        System.out.println("user name:"+fakeStoreConfig.getUserName());
    }

    public void fakeStoreConfigValueEnv() {
        String url = environment.getProperty("fakestore.url", "neka default value");
        System.out.println("url" + url);
    }
}
