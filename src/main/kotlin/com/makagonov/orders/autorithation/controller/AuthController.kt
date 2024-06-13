package com.makagonov.orders.autorithation.controller

import com.makagonov.orders.autorithation.service.SessionService
import com.makagonov.orders.autorithation.service.UserService
import io.jsonwebtoken.Jwts
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/auth")
class AuthController(@Autowired val userService: UserService, @Autowired val sessionService: SessionService) {

    @PostMapping("register")
    fun register(@RequestBody registerRequest: RegisterRequest): ResponseEntity<String> {
        val user = userService.registerUser(registerRequest.nickname, registerRequest.email, registerRequest.password)
        return ResponseEntity.ok("User ${user.userName} registered successfully")
    }

    @PostMapping("login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<String> {
        val user = userService.findUserByEmail(loginRequest.email)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password")

        if (BCryptPasswordEncoder().matches(loginRequest.password, user.password)) {
            val token = Jwts.builder()
                .setSubject(user.email)
                .setIssuedAt(Date())
                .setExpiration(Date(System.currentTimeMillis() + 3600000))
                .compact()
            sessionService.createSession(user, token)
            return ResponseEntity.ok(token)
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password")
    }

    @GetMapping("user")
    fun getUserInfo(request: HttpServletRequest): ResponseEntity<Any> {
        val token = request.getHeader("Authorization")?.substring(7)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing token")

        val session = sessionService.findSessionByToken(token)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token")

        return ResponseEntity.ok(session.user)
    }
}

data class RegisterRequest(val nickname: String, val email: String, val password: String)
data class LoginRequest(val email: String, val password: String)