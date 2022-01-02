package spring.aop.advice;

import org.springframework.aop.ThrowsAdvice;

public class LogAfterThrowingAdvice implements ThrowsAdvice{

	//ThrowAdvice는 어떠한 예외가 발생하냐에 따라 함수의 인자가 달라져 override를 하기 힘들다.
	
	public void afterThrowing(IllegalArgumentException e) throws Throwable {
		System.out.println("예외가 발생하였습니다 : " + e.getMessage());
	}
}
