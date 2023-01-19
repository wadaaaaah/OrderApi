package com.accenture.order.entity;

import lombok.Data;
import org.hibernate.annotations.Columns;

import javax.persistence.*;

@Entity
@Table(name = "order")
@Data
public class Demo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    String name;
    String description;
    int age_limit;
    Long stock;

}
