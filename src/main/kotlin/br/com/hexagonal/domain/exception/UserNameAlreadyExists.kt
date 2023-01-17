package br.com.hexagonal.domain.exception

class UserNameAlreadyExists(
    private val username: String
) : RuntimeException("username=[$username] already exists.")