package br.com.hexagonal.infrastructure.adapter.entity.repository

import br.com.hexagonal.domain.User
import br.com.hexagonal.domain.ports.repository.UserRepositoryPort
import br.com.hexagonal.infrastructure.adapter.entity.UserEntity

class UserRepository(
    private val repository: SpringUserRepository
) : UserRepositoryPort {

    override fun find(username: String): User? {
        return this.repository.findByUsername(username)
            ?.run {
                User(firstName = firstName, lastName = lastName, username = username)
            }
    }

    override fun save(user: User) {
        val entity = UserEntity(
            firstName = user.firstName,
            lastName = user.lastName,
            username = user.username
        )
        this.repository.save(entity)
    }

    override fun update(user: User) {
        this.repository.findByUsername(user.username)
            ?.run {
                val entity = UserEntity(
                    uuid = uuid,
                    firstName = user.firstName,
                    lastName = user.lastName,
                    username = user.username
                )
                repository.save(entity)
            }
    }

    override fun existsByUsername(username: String): Boolean {
        return this.repository.existsByUsername(username)
    }
}