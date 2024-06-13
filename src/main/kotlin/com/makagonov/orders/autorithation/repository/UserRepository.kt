package com.makagonov.orders.autorithation.repository

import com.makagonov.orders.autorithation.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}