package com.accenture.order.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WizardInfo {

    @JsonProperty("id")
    Long id;

    @JsonProperty("name")
    String name;

    @JsonProperty("active")
    boolean active;

    @JsonProperty("age")
    int age;

    @JsonProperty("joined_date")
    Date joinedDate;

}
