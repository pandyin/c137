package com.c137.common.model

sealed class Location(val name: String) {

    fun isUnknown(): Boolean = name == Unknown.name

    internal class LocationImpl(name: String) : Location(name)
    internal object Unknown : Location("unknown")
}

fun String.toLocation(): Location {
    if (this == Location.Unknown.name) {
        return Location.Unknown
    }
    return Location.LocationImpl(this)
}
