Kotlin 언어로 계산기 만들기

1. 기본적인 사칙연산 계산기
   사칙연산의 경우 +,-,*,/의 이항 연산자들 사이의 계산을 도와주는 Class를 정의하여 만들었다.
   간단하게 피연산자 두 개를 입력받고 연산자를 잘 입력하면 된다.
   
2. 공학용 계산기
   공백을 포함하여 오류가 없는 수식이 들어오는 경우 이를 PostFix 형태로 변경한다.
   변경된 수식을 계산하여 출력한다.
   사용가능한 함수는 다음 6가지이다. sin, cos, tan, log, exp, sqrt
   위의 함수를 사용할 때에는 반드시 뒤에 괄호가 등장해야한다.
   오류가 없는 뺄셈 단항연산자는 사용이 가능하다. ex) -1 * 3 = -3, -(3+(1*3)) = -9

   

   