package com.logate.summer.filter;

public class UserFilter {

    String city;
    Integer age;
    String skakac;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSkakac() {
        return skakac;
    }

    public void setSkakac(String skakac) {
        this.skakac = skakac;
    }

    @Override
    public String toString() {
        return "UserFilter{" +
                "city='" + city + '\'' +
                ", age=" + age +
                ", skakac='" + skakac + '\'' +
                '}';
    }
}