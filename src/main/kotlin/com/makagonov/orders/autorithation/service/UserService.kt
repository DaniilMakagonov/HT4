package com.makagonov.orders.autorithation.service

import com.makagonov.orders.autorithation.model.User
import com.makagonov.orders.autorithation.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(@Autowired val userRepository: UserRepository) {
    private val passwordEncoder = BCryptPasswordEncoder()

    fun registerUser(userName: String, email: String, password: String): User {
        val hashedPassword = passwordEncoder.encode(password)
        val user = User(userName = userName, email = email, password = hashedPassword)
        return userRepository.save(user)
    }

    fun findUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}