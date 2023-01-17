package br.com.hexagonal.infrastructure.config

import br.com.hexagonal.domain.adapter.service.UserServiceImpl
import br.com.hexagonal.domain.ports.`interface`.UserServicePort
import br.com.hexagonal.domain.ports.repository.UserRepositoryPort
import br.com.hexagonal.infrastructure.adapter.entity.repository.SpringUserRepository
import br.com.hexagonal.infrastructure.adapter.entity.repository.UserRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration(
    private val repository: SpringUserRepository
) {
    @Bean
    @ConditionalOnMissingBean(UserServicePort::class)
    fun userService(repositoryPort: UserRepositoryPort) = UserServiceImpl(repositoryPort)

    @Bean
    @ConditionalOnMissingBean(UserRepositoryPort::class)
    fun userRepository() = UserRepository(this.repository)
}