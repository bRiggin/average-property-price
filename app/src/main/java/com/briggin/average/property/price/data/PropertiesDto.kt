package com.briggin.average.property.price.data

import com.google.gson.annotations.SerializedName

data class PropertiesDto(
    @SerializedName("properties")
    val properties: List<PropertyDto>
)

data class PropertyDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("price")
    val price: Long,
    @SerializedName("bedrooms")
    val bedrooms: Long,
    @SerializedName("bathrooms")
    val bathrooms: Long,
    @SerializedName("number")
    val number: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("postcode")
    val postcode: String,
    @SerializedName("propertyType")
    val propertyType: String
)
