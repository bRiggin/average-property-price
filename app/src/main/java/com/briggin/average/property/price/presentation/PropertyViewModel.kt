package com.briggin.average.property.price.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.briggin.average.property.price.R
import com.briggin.average.property.price.domain.PropertyDataSource
import com.briggin.average.property.price.domain.PropertyDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.math.RoundingMode

class PropertyViewModel(private val dataSource: PropertyDataSource) : ViewModel() {

    private val _propertyAverage: MutableStateFlow<String> = MutableStateFlow("")
    val propertyAverage: Flow<String> = _propertyAverage

    private val _error: MutableStateFlow<Int?> = MutableStateFlow(null)
    val error: Flow<Int?> = _error

    suspend fun getPropertyAverage() {
        when (val domain = dataSource.getPropertyPrices()) {
            is PropertyDomain.Success ->
                with(domain.prices) {
                    if (isEmpty()) {
                        emitError(R.string.error_no_prices)
                    } else {
                        _propertyAverage.emit(average)
                        _error.emit(null)
                    }
                }
            PropertyDomain.Error -> emitError(R.string.error_unknown)
        }
    }

    private suspend fun emitError(@StringRes errorResID: Int) {
        _error.emit(errorResID)
        _propertyAverage.emit("")
    }
}

private val List<Long>.average: String get() =
    toLongArray().average().toBigDecimal().setScale(2, RoundingMode.UP).toString()
