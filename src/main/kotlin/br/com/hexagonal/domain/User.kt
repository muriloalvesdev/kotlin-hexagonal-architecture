package br.com.hexagonal.domain

import java.util.UUID

data class User(
    var id: UUID? = null,
    var firstName: String,
    var lastName: String,
    var username: String
)