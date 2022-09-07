package com.c137.data.model.dto

data class ResultsDto<MODEL>(
    val info: Info,
    val results: List<MODEL>
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)