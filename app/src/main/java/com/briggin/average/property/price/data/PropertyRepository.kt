package com.briggin.average.property.price.data

import com.briggin.average.property.price.domain.PropertyDataSource
import com.briggin.average.property.price.domain.PropertyDomain

class PropertyRepository(private val api: PropertyApi): PropertyDataSource {

    override suspend fun getDomain(): List<PropertyDomain> {
        TODO("Not yet implemented")
    }
}
