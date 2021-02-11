package com.briggin.average.property.price.presentation

import com.briggin.average.property.price.domain.PropertyDataSource
import com.briggin.average.property.price.domain.PropertyDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class PropertyViewModelParamTest(private val params: TestParams) {

    private val dataSource: PropertyDataSource = mockk()

    private lateinit var viewModel: PropertyViewModel

    @Before
    fun `configure test`() {
        viewModel = PropertyViewModel(dataSource)
    }

    @Test
    fun `GIVEN prices received WHEN requesting average property price THEN expect average`() =
        runBlocking {
            coEvery { dataSource.getPropertyPrices() } returns PropertyDomain.Success(params.prices)

            viewModel.getPropertyAverage()

            assertEquals(params.result, viewModel.propertyAverage.first())
            assertNull(viewModel.error.first())
        }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun errorCode() = listOf(
            TestParams(listOf(573924L, 5378L, 2481318L, 3578L, 24L, 2598L, 2481318L), "792591.15"),
            TestParams(listOf(7420L, 330835L, 8276L, 53781L, 12746L), "82611.60"),
            TestParams(listOf(23975738, 723792, 43671L), "8247733.67"),
            TestParams(listOf(7832L, 5378L, 3578L, 24L, 2598L, 8732L), "4690.34"),
            TestParams(listOf(1L, 1L, 1L, 1L, 1L, 1L), "1.00"),
            TestParams(listOf(1L, -1L, 1L, -1L, 1L, -1L), "0.00"),
            TestParams(listOf(1343L, 7L, 8134L, 91328L, 13L), "20165.00"),
            TestParams(listOf(98281L, 529L, 9384L, 2758L, 89213L, 8732L), "34816.17"),
            TestParams(listOf(1500000L, 635000L, 7892L, 4279L, 729L), "429580.00"),
            TestParams(listOf(50L, 53891L, 8935123L, 9824L, 892L, 892L, 8952084L), "2564679.43")
        )
    }
}

data class TestParams(val prices: List<Long>, val result: String)
