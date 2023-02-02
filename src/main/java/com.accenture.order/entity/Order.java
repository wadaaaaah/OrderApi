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
    @Column(name = "id", nullable = false)
    Long orderId;

    @Column(name = "wizard_id")
    Long wizardId;
    @Column(name = "magic_id")
    Long magicId;
    @Column(name = "magic_name")
    String magicName;
    @Column(name = "wizard_name")
    String wizardName;
    @Column(name = "stock")
    int stock;
    @Column(name = "age_limit")
    int ageLimit;

    public Order(){

    }

    public Order(Long orderId, Long wizardId, Long magicId, int stock){
        this.orderId = orderId;
        this.wizardId = wizardId;
        this.magicId = magicId;
        this.stock = stock;
    }
}
