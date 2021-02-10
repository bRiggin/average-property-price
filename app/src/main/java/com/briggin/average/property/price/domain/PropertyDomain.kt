package com.briggin.average.property.price.domain

sealed class PropertyDomain {
    data class Success(val prices: List<Long>): PropertyDomain()
    object Error: PropertyDomain()
}
