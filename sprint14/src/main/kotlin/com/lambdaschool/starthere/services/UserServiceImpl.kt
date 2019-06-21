package com.lambdaschool.starthere.services

import com.lambdaschool.starthere.models.Quote
import com.lambdaschool.starthere.models.User
import com.lambdaschool.starthere.models.UserRoles
import com.lambdaschool.starthere.repository.RoleRepository
import com.lambdaschool.starthere.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityNotFoundException
import java.util.ArrayList
import java.util.function.Consumer


@Service(value = "userService")
open class UserServiceImpl : UserDetailsService, UserService {

    @Autowired
    private val userrepos: UserRepository? = null

    @Autowired
    private val rolerepos: RoleRepository? = null

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userrepos!!.findByUsername(username)
                ?: throw UsernameNotFoundException("Invalid username or password.")
        return org.springframework.security.core.userdetails.User(user.username!!, user.getPassword()!!, user.authority)
    }

    @Throws(EntityNotFoundException::class)
    override fun findUserById(id: Long): User {
        return userrepos!!.findById(id).orElseThrow { EntityNotFoundException(java.lang.Long.toString(id)) }
    }

    override fun findAll(): List<User> {
        val list = ArrayList<User>()
        userrepos!!.findAll().iterator().forEachRemaining(Consumer<User> { list.add(it) })
        return list
    }

    override fun delete(id: Long) {
        if (userrepos!!.findById(id).isPresent) {
            userrepos.deleteById(id)
        } else {
            throw EntityNotFoundException(id.toString())
        }
    }

    @Transactional
    override fun save(user: User): User {
        val newUser = User()
        newUser.username = user.username
        newUser.setPasswordNoEncrypt(user.getPassword()!!)

        val newRoles = ArrayList<UserRoles>()
        for (ur in user.userRoles) {
            newRoles.add(UserRoles(newUser, ur.role!!))
        }
        newUser.userRoles = newRoles

        for (q in user.quotes) {
            (newUser.quotes as MutableList).add(Quote(q.quote!!, newUser))
        }

        return userrepos!!.save(newUser)
    }


    @Transactional
    override fun update(user: User, id: Long): User {
        val authentication = SecurityContextHolder.getContext().authentication
        val currentUser = userrepos!!.findByUsername(authentication.name)

        if (currentUser != null) {
            if (id == currentUser.userid) {
                if (user.username != null) {
                    currentUser.username = user.username
                }

                if (user.getPassword() != null) {
                    currentUser.setPasswordNoEncrypt(user.getPassword()!!)
                }

                if (user.userRoles.isNotEmpty()) {
                    // with so many relationships happening, I decided to go
                    // with old school queries
                    // delete the old ones
                    rolerepos!!.deleteUserRolesByUserId(currentUser.userid)

                    // add the new ones
                    for (ur in user.userRoles) {
                        rolerepos.insertUserRoles(id, ur.role!!.roleid)
                    }
                }

                if (user.quotes.isNotEmpty()) {
                    for (q in user.quotes) {
                        (currentUser.quotes as MutableList).add(Quote(q.quote!!, currentUser))
                    }
                }

                return userrepos.save(currentUser)
            } else {
                throw EntityNotFoundException(id.toString() + " Not current user")
            }
        } else {
            throw EntityNotFoundException(authentication.name)
        }

    }
}
