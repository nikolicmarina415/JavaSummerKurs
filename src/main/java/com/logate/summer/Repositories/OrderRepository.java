package com.logate.summer.Repositories;

import com.logate.summer.entities.Order;
import com.logate.summer.entities.OrderStatus;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    @Cacheable(value = "orders", key = "#id")
    Optional<Order> findById(Long id);

    @Cacheable(value = "orders", key = "'customer_' + #customerId")
    List<Order> findByCustomerId(Long customerId);

    @Override
    @CachePut(value = "orders", key = "#result.id")
    <S extends Order> S save(S order);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.status = :status WHERE o.id = :orderId")
    int updateOrderStatus(@Param("orderId") Long orderId, @Param("status") OrderStatus status);

    @Transactional
    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.order.id = :orderId")
    void deleteOrderItemsByOrderId(@Param("orderId") Long orderId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Order o WHERE o.id = :orderId")
    void deleteOrderById(@Param("orderId") Long orderId);
}
