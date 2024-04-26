package org.example

class BasicCalculator(
    private val adder: Calculator = Calculator(AddOperation()),
    private val subtractor: Calculator = Calculator(SubtractOperation()),
    private val divider: Calculator = Calculator(DivisionOperation()),
    private val remainder: Calculator = Calculator(RemainderOperation()),
    private val multiplier: Calculator = Calculator(MultiplicationOperation()),
    private var operand1: Int = 0,
    private var operand2: Int = 0,
    private var op: String = "+",
) {
    fun runBasicCalculator() {
        println("기본 계산기입니다.")
        getOperandAndOperator()
        try {
            basicOperate(operand1, operand2, op)
        } catch (e: Exception) {
            showInvalidInputMessage()
        }
        return
    }

    private fun getOperandAndOperator() {
        try {
            print("첫 번째 피연산자를 입력해주세요: ")
            operand1 = readln().toInt()
            print("두 번째 피연산자를 입력해주세요: ")
            operand2 = readln().toInt()
        } catch (e: NumberFormatException) {
            showInvalidInputMessage()
            return
        }
        print("연산자를 입력해주세요(+,-,*,/,%): ")
        op = readln()
        return
    }

    private fun basicOperate(num1: Int, num2: Int, op: String) {
        val result: Double = when (op) {
            "+" -> adder.operate(num1, num2)
            "-" -> subtractor.operate(num1, num2)
            "*" -> multiplier.operate(num1, num2)
            "/" -> divider.operate(num1, num2)
            "%" -> remainder.operate(num1, num2)
            else -> {
                throw Exception()
            }
        }
        println("결과 : $result")
        printLine()
    }
}