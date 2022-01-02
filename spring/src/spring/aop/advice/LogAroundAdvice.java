package spring.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		

		Object result = invocation.proceed(); //invoke가 proceed로 변경됨

		long end = System.currentTimeMillis();
		
		String message = (end - start) + " ms 시간 걸렸습니다.";
		System.out.println(message);
		return result;
	}

}
