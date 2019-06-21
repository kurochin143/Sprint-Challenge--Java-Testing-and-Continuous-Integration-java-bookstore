package com.lambdaschool.starthere

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableWebMvc
@EnableJpaAuditing
@SpringBootApplication
@EnableSwagger2
open class StartHereApplication

fun main(args: Array<String>) {
    val ctx = SpringApplication.run(StartHereApplication::class.java, *args)

    val dispatcherServlet = ctx.getBean("dispatcherServlet") as DispatcherServlet
    dispatcherServlet.setThrowExceptionIfNoHandlerFound(true)

}