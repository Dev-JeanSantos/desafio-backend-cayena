package com.cayena.backendkotlin.exceptions

import com.cayena.backendkotlin.exceptions.dtos.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class RestExceptionHandler {


    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlerNotFound(
        exception: NotFoundException,
        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handlerMethodArgumentTypeMismatchException(
        exception: MethodArgumentTypeMismatchException,
        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = exception.message,
            path = request.servletPath
        )
    }
    @ExceptionHandler(OperationNotPerformedException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handlerOperationNotPerformedException(
        exception: OperationNotPerformedException,
        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            error = HttpStatus.UNPROCESSABLE_ENTITY.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handlerBadRequest(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ErrorView {

        val errorMesage = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach{
            err -> errorMesage[err.field] = err.defaultMessage
        }

        return ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = errorMesage.toString(),
            path = request.servletPath
        )
    }
}