package br.com.hexagonal.domain.ports.repository

import br.com.hexagonal.domain.User

interface UserRepositoryPort {
    fun find(username: String): User?

    fun save(user: User)

    fun update(user: User)

    fun existsByUsername(username: String): Boolean
}