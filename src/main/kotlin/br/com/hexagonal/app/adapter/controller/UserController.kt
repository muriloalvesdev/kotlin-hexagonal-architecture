package br.com.hexagonal.app.adapter.controller

import br.com.hexagonal.domain.dto.UserDto
import br.com.hexagonal.domain.ports.`interface`.UserServicePort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("users")
class UserController(
    private val userServicePort: UserServicePort
) {

    @PostMapping
    fun save(@RequestBody userDto: UserDto): ResponseEntity<Any> {
        this.userServicePort.save(userDto)
        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("users/{username}")
                .buildAndExpand(userDto.username)
                .toUri()
        ).build<Any>()
    }

    @GetMapping("{username}")
    fun find(@PathVariable(name = "username") username: String) = this.userServicePort.find(username)

    @PutMapping
    fun update(@RequestBody userDto: UserDto) = this.userServicePort.update(userDto)
}