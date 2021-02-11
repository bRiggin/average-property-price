package com.briggin.average.property.price.data

import com.briggin.average.property.price.domain.PropertyDataSource
import com.briggin.average.property.price.domain.PropertyDomain
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException

class PropertyRepositoryTest {

    private val api: PropertyApi = mockk(relaxed = true)

    private lateinit var dataSource: PropertyDataSource

    @Before
    fun `configure test`() {
        dataSource = PropertyRepository(api)
    }

    @Test
    fun `WHEN property prices requested THEN expect api to be queried`() = runBlocking {
        dataSource.getPropertyPrices()
        coVerify { api.getProperties() }
    }

    @Test
    fun `GIVEN exception is thrown WHEN querying api THEN expect error domain`() = runBlocking {
        coEvery { api.getProperties() } throws IllegalStateException()

        assertEquals(PropertyDomain.Error, dataSource.getPropertyPrices())
    }

    @Test
    fun `GIVEN request is successful WHEN querying api THEN expect property prices`() =
        runBlocking {
            val propertyOneValue = 214325987L
            val propertyTwoValue = 89642L
            val propertyThreeValue = 89290564L
            val propertyOne: PropertyDto = mockk { every { price } returns propertyOneValue }
            val propertyTwo: PropertyDto = mockk { every { price } returns propertyTwoValue }
            val propertyThree: PropertyDto = mockk { every { price } returns propertyThreeValue }

            coEvery { api.getProperties() } returns PropertiesDto(
                listOf(propertyOne, propertyTwo, propertyThree)
            )

            val domain = dataSource.getPropertyPrices() as PropertyDomain.Success

            assertEquals(
                listOf(propertyOneValue, propertyTwoValue, propertyThreeValue),
                domain.prices
            )
        }
}
