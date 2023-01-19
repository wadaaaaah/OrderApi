package com.accenture.order.repository;

import com.accenture.order.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Demo,Long> {

    Demo findByName(String name);
}
