package com.lambdaschool.starthere.repository

import com.lambdaschool.starthere.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User
}
