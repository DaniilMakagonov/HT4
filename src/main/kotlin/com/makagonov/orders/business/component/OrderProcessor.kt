package com.makagonov.orders.business.component

import com.makagonov.orders.business.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class OrderProcessor(@Autowired val orderService: OrderService) {

    @Scheduled(fixedRate = 60000)
    fun processPendingOrders() {
        orderService.processOrders()
    }
}