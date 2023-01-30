package com.accenture.order.entity;

import com.sun.istack.NotNull;
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

    //@NotNull(message = "Wizard id should not be null")
    Long wizard_id;

    //@NotNull(message = "Wand id should not be null")
    Long magic_id;
    String magic_name;
    String wizard_name;
    int stock;
    int age_limit;

    public Order(){

    }

    public Order(Long orderId, Long wizardId, Long magicId, int stock){
        this.order_id = orderId;
        this.wizard_id = wizardId;
        this.magic_id = magicId;
        this.stock = stock;
    }



}
