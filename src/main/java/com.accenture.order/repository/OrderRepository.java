package com.accenture.order.repository;

import com.accenture.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findOrdersByWizardId(Long id);

}
