package com.briggin.average.property.price.domain

interface PropertyDataSource {
    suspend fun getDomain(): PropertyDomain
}
