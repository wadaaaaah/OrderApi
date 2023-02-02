package com.accenture.order.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MagicWand {

    @JsonProperty("id")
    Long id;
    @JsonProperty("name")
    String name;
    @JsonProperty("narrative")
    String narrative;
    @JsonProperty("age_limit")
    int ageLimit;
    @JsonProperty("stock")
    Long stock;

}
