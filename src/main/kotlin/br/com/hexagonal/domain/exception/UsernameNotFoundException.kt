package br.com.hexagonal.domain.exception

class UsernameNotFoundException(
    private val username: String
) : RuntimeException("username=[$username] not found.")