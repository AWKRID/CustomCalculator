package org.example

import kotlinx.coroutines.*

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
fun main() {
    while (true) {
        printMenu()
        try {
            getUserChoice()
        } catch (e: Exception) {
            return
        }
        sleep(1)
    }
}

fun sleep(seconds: Long) {
    runBlocking {
        launch {
            delay(seconds * 1000)
        }
    }
}


fun printMenu() {
    println("김민수의 계산기에 오신 것을 환영합니다!")
    println("1. 기본 계산기")
    println("2. 공학용 계산기")
    println("종료하시려면 아무키나 눌러주세요.")
    println("---------------------------------------")
}

fun getUserChoice() {
    val modeChoice: String = readln()
    val basicCalculator = BasicCalculator(
        adder = Calculator(AddOperation()),
        subtractor = Calculator(SubtractOperation()),
        divider = Calculator(DivisionOperation()),
        remainder = Calculator(RemainderOperation()),
        multiplier = Calculator(MultiplicationOperation()),
    )
    when (modeChoice) {
        "1" -> basicCalculator.runBasicCalculator()
        "2" -> runExpressionCalculator()
        else -> {
            println("프로그램을 종료합니다.")
            throw Exception()
        }
    }
}


fun showInvalidInputMessage() {
    println("올바르지 않은 입력입니다.")
    println("---------------------------------------")
}

fun showInvalidExpressionMessage() {
    println("유효하지 않은 수식입니다.")
    println("---------------------------------------")
}


fun runExpressionCalculator() {
    printExpressionCalculatorInfo()
    val expression: String = readln()
    val expressionCalculator = ExpressionCalculator(expression)
    try {
        expressionCalculator.operate()
    } catch (e: Exception) {
        showInvalidExpressionMessage()
    }
}

fun printExpressionCalculatorInfo() {
    println("공학용 계산기입니다.")
    println("사용가능한 함수 규칙은 아래와 같습니다.")
    println("사인함수 : sin, 코사인함수 : cos, 탄젠트 함수 : tan")
    println("e의 제곱 : exp, 자연로그 : log, 제곱근 : sqrt")
    println("위의 함수 사용시 반드시 괄호로 묶어주세요. 예시) sin(100) + sqrt(1000)")
    print("수식을 입력해주세요: ")
}

fun printLine() {
    println("----------------------------------------")
}