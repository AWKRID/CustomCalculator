# Kotlin 계산기 
---
## 🔍프로젝트 소개 
---
- **기본 계산기**와 **공학용 계산기** 기능을 가진 종합 계산이 프로그램입니다.
- **기본 계산기**: 입력된 피연산자 2개와 연산자 1개의 계산 결과를 알려주는 계산기입니다.
- **공학용 계산기**: 한줄의 문자열로 입력된 수식을 계산할 수 있는 계산기로 6가지 함수(sin, cos, tan, log, sqrt, exp)를 지원합니다.

## 💻사용 예시 
--- 
### 1. 프로그램 시작 시 계산기 종류 선택
```
김민수의 계산기에 오신 것을 환영합니다!
1. 기본 계산기
2. 공학용 계산기
종료하시려면 아무키나 눌러주세요.
---------------------------------------
```

### 2. 기본 계산기 사용
```
기본 계산기입니다.
첫 번째 피연산자를 입력해주세요: 40
두 번째 피연산자를 입력해주세요: 30
연산자를 입력해주세요(+,-,*,/,%): *
결과 : 1200.0
```

### 3. 공학용 계산기 사용
```
공학용 계산기입니다.
사용가능한 함수 규칙은 아래와 같습니다.
사인함수 : sin, 코사인함수 : cos, 탄젠트 함수 : tan
e의 제곱 : exp, 자연로그 : log, 제곱근 : sqrt
위의 함수 사용시 반드시 괄호로 묶어주세요. 예시) sin(100) + sqrt(1000)
수식을 입력해주세요: -sin(tan(10.23)+sqrt(102.5)) * (cos(100) + exp(2)/log(10.35))
결과 : 3.97
```

## ✏️구현 사항
---
### 1. 기본 요구 사항 구현 방법
- `AbstractOperation` 이름의 추상 클래스를 생성 후 상속을 통해 각 사칙연산 클래스 작성.
  
  `AbstractOperation.kt`
  ```Kotlin
  abstract class AbstractOperation {
    abstract fun operate(num1: Int, num2: Int): Double
   }
  ```
  
- `Calculator`클래스의 매개변수로 각 사칙연산 인스턴스를 주입.
  
  `Operation.kt`
  ```Kotlin
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
  ```
- 연산시 각 연산별 기능을 하는 클래스 선언 후 계산하기.
  
  ```Kotlin
   adder = Calculator(AddOperation()),
   subtractor = Calculator(SubtractOperation()),
   divider = Calculator(DivisionOperation()),
   remainder = Calculator(RemainderOperation()),
   multiplier = Calculator(MultiplicationOperation())
  ```

  ```Kotlin
  adder.operate(num1, num2)
  ```

### 2. 추가 구현 사항
입력된 수식의 계산 결과를 보여주는 프로그램을 구현하였다. 
사칙연산, 괄호, 함수 사이의 우선순위를 정해두고 이에 따라 중위 표기식으로 입력된 식을 후위 표기식으로 바꾸어 연산을 진행했다. 

-수식 계산을 위한 `ExpressionCalculator.kt` 구조는 아래와 같다. 함수 세부 내역은 생략하였다. 

`ExpressionCalculator.kt`

```Kotlin
class ExpressionCalculator(_exp: String) {
    private var exp: String = _exp

    fun operate() {}

    private fun calculateWithPostFixList(postFixList: List<String>): Double {}

    private fun trimExpression() {}

    private fun isValidExpression(): Boolean {}

    private fun functionalOperate(num: Double, op: String): Double {
        return when (op) {
            "m" -> -1 * num
            "s" -> sin(num)
            "c" -> cos(num)
            "t" -> tan(num)
            "l" -> ln(num)
            "e" -> exp(num)
            "r" -> sqrt(num)
            else -> throw Exception()
        }
    }

    private fun basicOperate(num1: Double, num2: Double, op: String): Double {
        return when (op) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> num2 / num1
            else -> throw Exception()
        }
    }

    private fun getPriority(op: Char): Int {
        return when (op) {
            in functionSignCharList -> 5
            'm' -> 4
            '*', '/' -> 3
            '+', '-' -> 2
            '(', ')' -> 1
            else -> -1
        }
    }

    private fun toPostFix(exp: String): MutableList<String> {}

    private fun isDigit(ch: Char): Boolean {}

}
```
- 가장 먼저 입력된 수식이 유효한 수식인지 확인하기 위해 `trimExpression` 함수로 공백을 제거하고, 단항 연산자의 뺄셈 그리고 sin 등의 함수를 사전에 정해둔 기호로 변경한다.
  
- `isValidExpression` 함수에서 `trimExpression`를 호출하여 수식을 변형하고, 해당 수식이 허용하지 않는 기호를 포함하는지 등의 여부를 판단한다.
  
- 입력된 수식에 이상이 없는 경우 `toPostFix` 함수를 통해 후위 표기식으로 변환한다.
- 변환된 후위 표기식을 `calculateWithPostFixList`를 통해 계산하고 이를 출력한다.

