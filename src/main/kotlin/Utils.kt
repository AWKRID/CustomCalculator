package org.example

import java.util.*

fun getPriority(op: Char):Int {
    return when (op) {
        '*','/' -> 3
        '+','-' -> 2
        '(',')' -> 1
        else -> -1
    }
}

fun toPostFix(exp:String) : String {
    var ch : Char
    var stk : Stack<Char> = Stack<Char>()
    var postFixExpression : String = ""
    for(i in exp.indices){
        ch = exp[i]
        when(ch){
            '+','-','*','/' -> {
                while(stk.isNotEmpty() && getPriority(stk.peek()) >= getPriority(ch)){
                    postFixExpression += stk.pop()
                }
                stk.push(ch)
            }
            '(' -> stk.push(ch)
            ')' -> {
                while(stk.peek()!='('){
                    postFixExpression += stk.pop()
                }
                stk.pop()
            }
            in '0'..'9'->{
                postFixExpression += ch
            }
        }
    }
    while(stk.isNotEmpty()) postFixExpression += stk.pop()
    return postFixExpression
}

fun isDigit(ch : Char) : Boolean{
    return ch in '0'..'9'
}