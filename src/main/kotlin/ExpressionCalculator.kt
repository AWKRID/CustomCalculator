package org.example

import java.util.*

class ExpressionCalculator(_exp: String) {
    private var exp: String = _exp

    fun operate(): Double {
        val stk: Stack<Double> = Stack<Double>()
        if(!isValidExpression()){
            throw Exception()

        }
        val postFixList = toPostFix(this.exp)
        for (element in postFixList) {
            if (isDigit(element[0])) {
                stk.push(element.toDouble())
            } else {
                val num1 = stk.pop().toDouble()
                val num2 = stk.pop().toDouble()
                stk.push(basicOperate(num1, num2, element))
            }
        }
        return stk.pop()
    }
    private fun isValidExpression() : Boolean{
        exp = exp.replace("\\s+".toRegex(), "")
        println(exp)
        var ret = true
        for(i in exp.indices){
            when(exp[i]){
                '+', '-', '*', '/', '(', ')' -> continue
                in '0'..'9' -> continue
                else -> {
                    ret = false
                    break
                }
            }
        }
        return ret
    }
    private fun basicOperate(num1: Double, num2: Double, op: String): Double {
        return when (op) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> num1 / num2
            else -> -999999.0
        }
    }

    private fun getPriority(op: Char): Int {
        return when (op) {
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
                '+', '-', '*', '/' -> {
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

                in '0'..'9' -> {
                    while (i + j < exp.length && isDigit(exp[i + j])) {
                        postFixExpression += exp[i + j].toString()
                        j++
                    }
                    postFixExpression += " "
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
        return ch in '0'..'9'
    }
}
