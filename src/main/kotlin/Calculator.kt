package org.example

class BasicCalculator(private val operation: AbstractOperation) {
    fun operate(num1: Int, num2: Int): Double {
        return operation.operate(num1, num2)
    }
}