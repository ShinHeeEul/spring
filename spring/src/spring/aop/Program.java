package spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.aop.entity.Exam;
import spring.aop.entity.NewlecExam;
import spring.di.newlecDIConfig;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("/spring/aop/setting.xml");
		//		new AnnotationConfigApplicationContext(newlecDIConfig.class);
		
		Exam proxy = (Exam) context.getBean("proxy");
		
		
//		
//		Exam exam = new NewlecExam(1,1,1,1);
//		
//		/////////////////////////////////////////////////
//		// 순수 자바로만 구성한 코드
//		// proxy : 곁다리 업무를 갖고 있는 녀석
//		Exam proxy = (Exam) Proxy.newProxyInstance(/*실질적인 객체 로드*/NewlecExam.class.getClassLoader(),
//				/*interface 정보, 복수형*/ new Class[] {Exam.class},
//				/*곁다리 업무를 꽂을 수 있는 부분*/
//				// 익명의 클래스를 간단하게 만듦 - 클래스 파일을 생성하여 달아주어도 됨
//				new InvocationHandler() {
//					
//
//					// 핵심 포인트
//					
//					@Override
//					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//						// TODO Auto-generated method stub
//						
//						long start = System.currentTimeMillis();
//						
//						Object result = method.invoke(/*실제 업무 객체명*/exam, /*호출 메서드 파라미터*/args);
//						
//						
//						
//						long end = System.currentTimeMillis();
//						
//						String message = (end - start) + " ms 시간 걸렸습니다.";
//						System.out.println(message);
//						return result;
//					}
//				}
//				); 
		
		System.out.printf("total is %d\n",proxy.total());
		System.out.printf("avg is %f\n",proxy.avg());
	}

}
