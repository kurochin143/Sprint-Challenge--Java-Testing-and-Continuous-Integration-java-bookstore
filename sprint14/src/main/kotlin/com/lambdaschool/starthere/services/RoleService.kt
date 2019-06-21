package com.lambdaschool.starthere.services

import com.lambdaschool.starthere.models.Role

interface RoleService {
    fun findAll(): List<Role>

    fun findRoleById(id: Long): Role

    fun delete(id: Long)

    fun save(role: Role): Role

    fun findByName(name: String): Role
}