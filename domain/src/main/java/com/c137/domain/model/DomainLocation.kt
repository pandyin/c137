package com.c137.domain.model

data class DomainLocation(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
) : BaseDomainModel()