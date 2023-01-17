package br.com.hexagonal.provider

import br.com.hexagonal.BaseUnitTest.Companion.FIRST_NAME
import br.com.hexagonal.BaseUnitTest.Companion.LAST_NAME
import br.com.hexagonal.BaseUnitTest.Companion.USERNAME
import br.com.hexagonal.domain.dto.UserDto
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

class UserDtoProviderTests : ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
        return Stream.of(
            UserDto(
                username = USERNAME,
                firstName = FIRST_NAME,
                lastName = LAST_NAME
            )
        ).map(Arguments::of)
    }
}