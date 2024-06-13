package com.makagonov.orders.autorithation.repository

import com.makagonov.orders.autorithation.model.Session
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<Session, Long> {
    fun findByToken(token: String): Session?
}