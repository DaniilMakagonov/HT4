package com.makagonov.orders.business.controller

import com.makagonov.orders.business.model.Order
import com.makagonov.orders.business.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrderController(@Autowired val orderService: OrderService) {

    @PostMapping
    fun createOrder(@RequestBody createOrderRequest: CreateOrderRequest): ResponseEntity<String> {
        val order = orderService.createOrder(
            createOrderRequest.userId,
            createOrderRequest.fromStationId,
            createOrderRequest.toStationId
        )
        return ResponseEntity.ok("Order created with id ${order.id}")
    }

    @GetMapping("/{orderId}")
    fun getOrder(@PathVariable orderId: Long): ResponseEntity<Order> {
        val order = orderService.getOrderById(orderId)
        return ResponseEntity.ok(order)
    }
}

data class CreateOrderRequest(val userId: Long, val fromStationId: Long, val toStationId: Long)