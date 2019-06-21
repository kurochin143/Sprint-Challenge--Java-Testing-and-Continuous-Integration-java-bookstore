package com.lambdaschool.starthere.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.*
import java.util.ArrayList

@Entity
@Table(name = "books")
class Book : Auditable {

    @Id
    @GeneratedValue
    var bookid: Long = 0

    var booktitle: String? = null
    var isbn: String? = null
    var copy: Int = 0

    @ManyToMany
    @JoinTable(name = "bookauthors", joinColumns = [JoinColumn(name = "bookid")], inverseJoinColumns = [JoinColumn(name = "authorid")])
    @JsonIgnoreProperties("books")
    var authors: List<Author> = mutableListOf()

    constructor() {}

    constructor(booktitle: String?, isbn: String?, copy: Int) {
        this.booktitle = booktitle
        this.isbn = isbn
        this.copy = copy
    }
}
