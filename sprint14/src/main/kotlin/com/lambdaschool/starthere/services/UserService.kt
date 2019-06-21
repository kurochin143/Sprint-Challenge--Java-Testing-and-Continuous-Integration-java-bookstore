package com.lambdaschool.starthere.services

import com.lambdaschool.starthere.models.User

interface UserService {

    fun findAll(): List<User>

    fun findUserById(id: Long): User

    fun delete(id: Long)

    fun save(user: User): User

    fun update(user: User, id: Long): User
}