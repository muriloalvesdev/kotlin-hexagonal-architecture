package br.com.hexagonal

import org.junit.jupiter.api.Tag
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@Tag("unit-tests")
open class BaseUnitTest {
    companion object {
        const val USERNAME = "muriloalvesdev"
        const val FIRST_NAME = "Murilo"
        const val LAST_NAME = "Batista"
    }
}