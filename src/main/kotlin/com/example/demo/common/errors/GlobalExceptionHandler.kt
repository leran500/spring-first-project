package com.example.demo.common.errors

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(exception: MethodArgumentNotValidException): ResponseEntity<String> {
        val errors = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach { error ->
            errors[error.field] =
                "${if (errors[error.field] != null) "${errors[error.field]} ," else ""} ${error.defaultMessage}"
        }

        val sb = StringBuilder()
        errors.forEach { entry ->
            sb.append("${entry.key}: ${entry.value} ")
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("An error occurred while validating the DTO fields: \n ${sb}")
    }

    @ExceptionHandler(JpaObjectRetrievalFailureException::class)
    fun handleJPAnOtFoundExceptions(exception: JpaObjectRetrievalFailureException, req: HttpServletRequest): ResponseEntity<String> {
        println("1")
        println(exception)
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("The requested resource is not found: ${exception.message}")
    }

    //handle other errors
    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleException(exception: Exception): ResponseEntity<String> {
        println("2")
        println(exception)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: ${exception.message}")
    }

}
