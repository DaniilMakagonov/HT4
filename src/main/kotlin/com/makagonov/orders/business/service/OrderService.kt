package com.makagonov.orders.business.service

import com.makagonov.orders.autorithation.repository.UserRepository
import com.makagonov.orders.business.model.Order
import com.makagonov.orders.business.repository.OrderRepository
import com.makagonov.orders.business.repository.StationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random

@Service
class OrderService(
    @Autowired val orderRepository: OrderRepository,
    @Autowired val stationRepository: StationRepository,
    @Autowired val userRepository: UserRepository
) {
    @Transactional
    fun createOrder(userId: Long, fromStationId: Long, toStationId: Long): Order {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("User not found") }
        val fromStation =
            stationRepository.findById(fromStationId).orElseThrow { RuntimeException("From station not found") }
        val toStation = stationRepository.findById(toStationId).orElseThrow { RuntimeException("To station not found") }

        val order = Order(user = user, fromStation = fromStation, toStation = toStation, status = 1)
        return orderRepository.save(order)
    }

    fun getOrderById(orderId: Long): Order {
        return orderRepository.findById(orderId).orElseThrow { RuntimeException("Order not found") }
    }

    fun processOrders() {
        val orders = orderRepository.findAll().filter { it.status == 1 }
        for (order in orders) {
            val newStatus = if (Random.nextBoolean()) 2 else 3
            order.status = newStatus
            orderRepository.save(order)
        }
    }
}