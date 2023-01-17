package br.com.hexagonal.app.adapter.controller

import br.com.hexagonal.BaseUnitTest
import br.com.hexagonal.domain.dto.UserDto
import br.com.hexagonal.domain.ports.`interface`.UserServicePort
import br.com.hexagonal.provider.UserDtoProviderTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

class UserControllerUnitUnitTest : BaseUnitTest() {
    private val userServicePort: UserServicePort = mock(UserServicePort::class.java)
    private val controller: UserController = UserController(this.userServicePort)

    @BeforeEach
    fun setUp() {
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))
    }

    @ParameterizedTest
    @ArgumentsSource(UserDtoProviderTests::class)
    fun shouldSave(userDto: UserDto) {
        //GIVEN
        doNothing().`when`(this.userServicePort).save(userDto)

        //WHEN
        val response = this.controller.save(userDto)

        //THEN
        assertThat(HttpStatus.CREATED).isEqualTo(response.statusCode)
        assertThat(response.headers.location).isNotNull
        assertThat("http://localhost/users/muriloalvesdev")
            .isEqualTo(response.headers.location.toString())

        verify(this.userServicePort, times(1))
    }
}