package com.makagonov.orders.autorithation.service

import com.makagonov.orders.autorithation.model.Session
import com.makagonov.orders.autorithation.model.User
import com.makagonov.orders.autorithation.repository.SessionRepository
import com.makagonov.orders.autorithation.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SessionService(
    @Autowired val sessionRepository: SessionRepository,
    @Autowired val userRepository: UserRepository
) {
    fun createSession(user: User, token: String): Session {
        val expires = LocalDateTime.now().plusHours(1)
        val session = Session(user = user, token = token, expires = expires)
        return sessionRepository.save(session)
    }

    fun findSessionByToken(token: String): Session? {
        return sessionRepository.findByToken(token)
    }
}