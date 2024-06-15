package com.logate.summer.dto.fakestore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Geolocation {

    String lat;
    @JsonProperty("long")
    String longi;
}