package com.accenture.order.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "wizard_info")
public class WizardInfo {

    @Id
    Long id;

    String name;
    boolean active;
    int age;
    Date joined_date;

}
