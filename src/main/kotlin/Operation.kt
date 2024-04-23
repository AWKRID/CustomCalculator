package org.example

class AddOperation : AbstractOperation() {
    override fun operate(num1: Int, num2: Int): Double {
        return (num1 + num2).toDouble()
    }
}

class SubtractOperation : AbstractOperation() {
    override fun operate(num1: Int, num2: Int): Double {
        return (num1 - num2).toDouble()
    }
}

class MultiplicationOperation : AbstractOperation() {
    override fun operate(num1: Int, num2: Int): Double {
        return (num1 * num2).toDouble()
    }
}

class DivisionOperation : AbstractOperation() {
    override fun operate(num1: Int, num2: Int): Double {
        return (num1 / num2).toDouble()
    }
}

class RemainderOperation : AbstractOperation() {
    override fun operate(num1: Int, num2: Int): Double {
        return (num1 % num2).toDouble()
    }
}
