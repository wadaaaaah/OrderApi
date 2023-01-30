package com.accenture.order.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "magic_wand")
public class MagicWand {

    @Id
    Long id;
    String name;
    String narrative;
    int age_limit;
    Long stock;
}
