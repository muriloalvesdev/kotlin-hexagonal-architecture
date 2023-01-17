package br.com.hexagonal.domain.ports.`interface`

import br.com.hexagonal.domain.dto.UserDto

interface UserServicePort {
    fun find(username: String): UserDto

    fun save(userDto: UserDto)

    fun update(userDto: UserDto)
}