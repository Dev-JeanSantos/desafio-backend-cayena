package com.cayena.backendkotlin.exceptions

data class NotFoundException(override val message: String): java.lang.RuntimeException(message)
