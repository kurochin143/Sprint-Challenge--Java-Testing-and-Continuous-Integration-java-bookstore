package com.lambdaschool.starthere.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.*

@Entity
@Table(name = "quotes")
class Quote : Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var quotesid: Long = 0

    @Column(nullable = false)
    var quote: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties("quotes", "hibernateLazyInitializer")
    var user: User? = null

    constructor() {}

    constructor(quote: String, user: User) {
        this.quote = quote
        this.user = user
    }
}