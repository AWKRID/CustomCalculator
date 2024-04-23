package org.example

import java.util.*
import kotlin.math.*

// sine -> s
// cosine -> c
// tan -> t
// logarithm(자연로그) -> l
// exponential(e^x) -> e
// square root -> r
// first minus -> m

class ExpressionCalculator(_exp: String) {
    private var exp: String = _exp

    fun operate(): Double {
        val stk: Stack<Double> = Stack<Double>()
        if (!isValidExpression()) {
            throw Exception()

        }
        val postFixList = toPostFix(this.exp)
        for (element in postFixList) {
            if (isDigit(element[0])) {
                stk.push(element.toDouble())
            } else {
                if (element in listOf("m","s","c","t","l","e","r")) {
                    val num1 = stk.pop().toDouble()
                    stk.push(functionalOperate(num1, element))
                }
                else{
                    val num1 = stk.pop().toDouble()
                    val num2 = stk.pop().toDouble()
                    stk.push(basicOperate(num1, num2, element))
                }

            }
        }
        return round(stk.pop()*100)/100
    }
    private fun changeExpression() {
        if(exp[0] == '-') exp = "m" + exp.substring(1)
        exp = exp.replace("sin","s")
        exp = exp.replace("cos","c")
        exp = exp.replace("tan","t")
        exp = exp.replace("log","l")
        exp = exp.replace("exp","e")
        exp = exp.replace("sqrt","r")
        return
    }
    private fun isValidExpression(): Boolean {
        exp = exp.replace("\\s+".toRegex(), "")
        changeExpression()
        println(exp)
        var ret = true
        for (i in exp.indices) {
            when (exp[i]) {
                's','c','t','l','e','r' -> {
                    if (exp[i+1] != '(') {
                        ret = false
                        break
                    }
                }
                '.', 'm', '+', '-', '*', '/', '(', ')' -> continue
                in '0'..'9' -> continue
                else -> {
                    ret = false
                    break
                }
            }
        }
        return ret
    }
    private fun functionalOperate(num1: Double, op : String) : Double{
        return when(op){
            "m" -> -1 * num1
            "s" -> sin(num1)
            "c" -> cos(num1)
            "t" -> tan(num1)
            "l" -> ln(num1)
            "e" -> exp(num1)
            "r" -> sqrt(num1)
            else -> throw Exception()
        }
    }
    private fun basicOperate(num1: Double, num2: Double, op: String): Double {
        return when (op) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> num1 / num2
            else -> throw Exception()
        }
    }

    private fun getPriority(op: Char): Int {
        return when (op) {
            's','c','t','l','e','r' -> 5
            'm' -> 4
            '*', '/' -> 3
            '+', '-' -> 2
            '(', ')' -> 1
            else -> -1
        }
    }

    private fun toPostFix(exp: String): MutableList<String> {
        var ch: Char
        val stk: Stack<Char> = Stack<Char>()
        val blank = " "
        var postFixExpression = ""
        var j = 0
        for (i in exp.indices) {
            if (j > 0) {
                j--
                continue
            }
            ch = exp[i]
            when (ch) {
                '+', '-', '*', '/','m','s','c','t','l','e','r' -> {
                    while (stk.isNotEmpty() && getPriority(stk.peek()) >= getPriority(ch)) {
                        postFixExpression += stk.pop().toString() + blank
                    }
                    stk.push(ch)
                }

                '(' -> stk.push(ch)
                ')' -> {
                    while (stk.peek() != '(') {
                        postFixExpression += stk.pop().toString() + blank
                    }
                    stk.pop()
                }

                in '0'..'9', '.' -> {
                    while (i + j < exp.length && isDigit(exp[i + j])) {
                        postFixExpression += exp[i + j].toString()
                        j++
                    }
                    postFixExpression += blank
                    j--
                }
            }
        }
        while (stk.isNotEmpty()) postFixExpression += stk.pop().toString() + blank
        val ret: MutableList<String> = postFixExpression.split(" ").toMutableList()
        ret.removeLast()
        return ret
    }


    private fun isDigit(ch: Char): Boolean {
        return ch in '0'..'9' || ch =='.'

    }
}
