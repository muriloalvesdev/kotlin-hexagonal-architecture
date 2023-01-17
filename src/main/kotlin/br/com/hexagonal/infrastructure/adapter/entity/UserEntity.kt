package br.com.hexagonal.infrastructure.adapter.entity

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    val uuid: UUID = UUID.randomUUID(),

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "last_name")
    val lastName: String,

    @Column(name = "username", unique = true)
    val username: String,
)