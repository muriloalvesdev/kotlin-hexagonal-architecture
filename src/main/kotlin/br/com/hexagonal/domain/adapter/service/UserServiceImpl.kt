package br.com.hexagonal.domain.adapter.service

import br.com.hexagonal.domain.User
import br.com.hexagonal.domain.dto.UserDto
import br.com.hexagonal.domain.exception.UserNameAlreadyExists
import br.com.hexagonal.domain.exception.UsernameNotFoundException
import br.com.hexagonal.domain.ports.`interface`.UserServicePort
import br.com.hexagonal.domain.ports.repository.UserRepositoryPort
import java.util.Objects.isNull

class UserServiceImpl(
    private val repositoryPort: UserRepositoryPort
) : UserServicePort {

    override fun find(username: String): UserDto {
        val user = this.repositoryPort.find(username)
        checkUserExists(user, username)
        return UserDto(user!!.username, user.firstName, user.lastName)
    }

    override fun save(userDto: UserDto) {
        if (this.repositoryPort.existsByUsername(userDto.username)) {
            throw UserNameAlreadyExists(userDto.username)
        }

        val user = User(
            username = userDto.username,
            firstName = userDto.firstName,
            lastName = userDto.lastName
        )

        this.repositoryPort.save(user)
    }

    override fun update(userDto: UserDto) {
        val username = userDto.username
        val user = this.repositoryPort.find(username)
        checkUserExists(user, username)

        user!!.firstName = userDto.firstName
        user.lastName = userDto.lastName

        this.repositoryPort.update(user)
    }

    private fun checkUserExists(user: User?, username: String) {
        if (isNull(user)) throw UsernameNotFoundException("username=[$username] not found.")
    }
}