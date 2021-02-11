package com.briggin.average.property.price.presentation

import com.briggin.average.property.price.R
import com.briggin.average.property.price.domain.PropertyDataSource
import com.briggin.average.property.price.domain.PropertyDomain
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PropertyViewModelTest {

    private val dataSource: PropertyDataSource = mockk()

    private lateinit var viewModel: PropertyViewModel

    @Before
    fun `configure test`() {
        viewModel = PropertyViewModel(dataSource)
    }

    @Test
    fun `WHEN average property price requested THEN expect`() = runBlocking {
        coEvery { dataSource.getPropertyPrices() } returns PropertyDomain.Error

        viewModel.getPropertyAverage()

        coVerify { dataSource.getPropertyPrices() }
    }

    @Test
    fun `GIVEN error received WHEN requesting average property price THEN expect error message`() =
        runBlocking {
            coEvery { dataSource.getPropertyPrices() } returns PropertyDomain.Error

            viewModel.getPropertyAverage()

            assertEquals(viewModel.propertyAverage.first(), "")
            assertEquals(viewModel.error.first(), R.string.error_unknown)
        }

    @Test
    fun `GIVEN no prices received WHEN requesting average property price THEN expect error message`() =
        runBlocking {
            coEvery { dataSource.getPropertyPrices() } returns PropertyDomain.Success(emptyList())

            viewModel.getPropertyAverage()

            assertEquals(viewModel.propertyAverage.first(), "")
            assertEquals(viewModel.error.first(), R.string.error_no_prices)
        }
}
