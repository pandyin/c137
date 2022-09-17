package com.c137.domain.model

data class PresentationLocation(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<Int>
) : BasePresentationModel() {

    private val hashResidents = residents.toHashSet()
    fun isResident(id: Int) = hashResidents.contains(id)

    fun isUnknown() = id == 0
}