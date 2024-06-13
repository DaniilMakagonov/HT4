package com.makagonov.orders.business.repository

import com.makagonov.orders.business.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>
