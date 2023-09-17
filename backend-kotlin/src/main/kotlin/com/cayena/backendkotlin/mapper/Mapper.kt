package com.cayena.backendkotlin.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}
