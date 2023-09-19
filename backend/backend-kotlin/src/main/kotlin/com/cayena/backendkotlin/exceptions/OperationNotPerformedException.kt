package com.cayena.backendkotlin.exceptions

data class OperationNotPerformedException(override val message: String): java.lang.RuntimeException(message)
