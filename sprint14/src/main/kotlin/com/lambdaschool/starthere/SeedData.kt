package com.lambdaschool.starthere

import com.lambdaschool.starthere.models.Quote
import com.lambdaschool.starthere.models.Role
import com.lambdaschool.starthere.models.User
import com.lambdaschool.starthere.models.UserRoles
import com.lambdaschool.starthere.services.RoleService
import com.lambdaschool.starthere.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

import java.util.ArrayList

@Transactional
@Component
open class SeedData : CommandLineRunner {
    @Autowired
    internal var roleService: RoleService? = null

    @Autowired
    internal var userService: UserService? = null

    @Throws(Exception::class)
    override fun run(args: Array<String>) {
        val adminRole = Role("admin")
        val userRole = Role("user")
        val dataRole = Role("data")

        roleService!!.save(adminRole)
        roleService!!.save(userRole)
        roleService!!.save(dataRole)

        // admin, data, user
        val admins = ArrayList<UserRoles>()
        admins.add(UserRoles(User(), adminRole))
        admins.add(UserRoles(User(), userRole))
        admins.add(UserRoles(User(), dataRole))
        val u1 = User("admin", "password", admins)
        (u1.quotes as MutableList).add(Quote("A creative man is motivated by the desire to achieve, not by the desire to beat others", u1))
        (u1.quotes as MutableList).add(Quote("The question isn't who is going to let me; it's who is going to stop me.", u1))
        userService!!.save(u1)

        // data, user
        val datas = ArrayList<UserRoles>()
        datas.add(UserRoles(User(), dataRole))
        datas.add(UserRoles(User(), userRole))
        val u2 = User("data", "password", datas)
        userService!!.save(u2)

        // user
        val users = ArrayList<UserRoles>()
        users.add(UserRoles(User(), userRole))
        val u3 = User("user", "password", users)
        (u3.quotes as MutableList).add(Quote("Live long and prosper", u3))
        (u3.quotes as MutableList).add(Quote("The enemy of my enemy is the enemy I kill last", u3))
        (u3.quotes as MutableList).add(Quote("Beam me up", u3))
        userService!!.save(u3)


    }
}