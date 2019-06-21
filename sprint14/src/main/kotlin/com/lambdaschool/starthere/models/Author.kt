package com.lambdaschool.starthere.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.*
import java.util.ArrayList

@Entity
@Table(name = "authors")
class Author : Auditable {

    @Id
    @GeneratedValue
    var authorid: Long = 0

    var lastname: String? = null
    var firstname: String? = null

    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("books")
    var books: List<Book> = mutableListOf()

    constructor() {}

    constructor(lastname: String?, firstname: String?) {
        this.lastname = lastname
        this.firstname = firstname
    }
}
