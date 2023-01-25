package com.accenture.order.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long order_id;

    Long wizard_id;
    Long magic_id;
    String wizard_name;
    String magic_name;
    int age_limit;



}
