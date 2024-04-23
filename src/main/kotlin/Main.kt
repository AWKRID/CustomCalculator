package org.example


//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
fun main() {
    var modeChoice: String
    while (true) {
        println("김민수의 계산기에 오신 것을 환영합니다!")
        println("1. 기본 계산기")
        println("2. 수식 계산기")
        println("종료하시려면 아무키나 눌러주세요.")
        println("---------------------------------------")
        modeChoice = readln()
        when (modeChoice) {
            "1" -> runBasicCalculator()
            "2" -> runExpressionCalculator()
            else -> {
                println("프로그램을 종료합니다.")
                break
            }
        }
    }
}

fun runBasicCalculator() {
    val adder = BasicCalculator(AddOperation())
    val subtractor = BasicCalculator(SubtractOperation())
    val divider = BasicCalculator(DivisionOperation())
    val remainder = BasicCalculator(RemainderOperation())
    val multiplier = BasicCalculator(MultiplicationOperation())
    println("기본 계산기입니다.")
    val a: Int
    val b: Int

    try {
        print("첫 번째 피연산자를 입력해주세요: ")
        a = readln().toInt()
        print("두 번째 피연산자를 입력해주세요: ")
        b = readln().toInt()
    } catch (e: NumberFormatException) {
        println("올바르지 않은 입력입니다.")
        println("---------------------------------------")
        return
    }
    print("연산자를 입력해주세요(+,-,*,/,%): ")
    val op: String = readln()
    print("결과 : ")
    when (op) {
        "+" -> println(adder.operate(a, b))
        "-" -> println(subtractor.operate(a, b))
        "*" -> println(multiplier.operate(a, b))
        "/" -> println(divider.operate(a, b))
        "%" -> println(remainder.operate(a, b))
        else -> {
            println("올바르지 않은 입력입니다. ")
        }
    }
    println("---------------------------------------")
}

fun runExpressionCalculator() {

    println("수식 계산기입니다.")
    print("수식을 입력해주세요: ")
    val expression: String = readln()
    val expressionCalculator = ExpressionCalculator(expression)
    try{
        println(expressionCalculator.operate())
    }catch (e: Exception){
        println("유효하지 않은 수식입니다.")
    }

    println("---------------------------------------")
}