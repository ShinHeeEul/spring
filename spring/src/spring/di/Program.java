package spring.di;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.di.entity.Exam;
import spring.di.entity.NewlecExam;
import spring.di.ui.ExamConsole;

/* *** DI(Dependency Injection) - 의존성 주입 -> 외부 파일에서 명령하는 형태
 * 
 * java 코드에 변수를 설정하는 두가지 방법이 있음
 * 1. xml등의 외부 파일을 이용하는 방법
 * 2. 어노테이션을 이용하여 코드내에 심는 방법
 * 	* 어노테이션 : 메타 데이터를 위해 사용되는 것 @로 사용
 * 
 * @Component : class 파일을 열어보고 @Compoment가 달려있는 것을 객체화 시킴
 * 		-- Component 위치 : class 위
 * 		Component를 읽어야 객체 생성이 가능한데 Component는 스프링에서 관여하지 않는다. 그러므로 xml에서 특수 명령어가 추가로 필요하다 -> xml 파일에서 참고
 * 		Component로 객체를 생성할 경우 초기값을 어떻게 설정하는지에 대한 의문이 남는다. 이를 해결하기 위한 어노테이션 -> @value
 * 		
 * 		-> 사실 컴포넌트를 붙여서 만드는 클래스는 객체화 가능 -> mvc방법으로 웹 애플리케이션을 만들 경우에 웹 앱을 구성하는 자바 코드(업무용 로직)을 컴포넌트를 한다. 
 * 		-> 그러므로 의미론적으로 Controller, Service, Repository 세가지로 세분화된다. - 객체화하고자 하는 클래스가 어떤 역할을 하는지 알려주므로 좀더 바람직함.
 * 		-> 이러한 Component들은 entity(객체)나 모델에 사용되기 적합하지 않다. -> 그렇다면? xml의 bean을 계속 사용해야되는건가? -> @Configuration 참조
 * 
 * @Value : Component로 객체를 생성할 경우 초기값 설정을 위해 사용되는 어노테이션
 * 
 * @Autowired : 세터가 사용하는 변수 또는 세터에 @Autowired를 설정하면 property 역할을 할 수 있다.
 * 	* 무엇을 근거로 자동으로 연결해주나? ref=exam나 p:kor 같은 부분도 없는데?
 * 		Autowired된 함수의 파라미터의 자료형을 근거로 autiowired
 * 		그렇다면 똑같은 자료형의 bean이 두개가 있다면? - Autowired된 함수의 파라미터의 변수명과 맞춰줌
 * 		변수명이 다를 경우 : @Qualifier("id값") - gridExamConsole 참조
 * 		-- Autowired 위치 : setter 함수, 필드 변수(기본 생성자에서 객체를 바인딩하는 작업), 오버로드 생성자
 * 
 * 		Autowired를 객체가 없을 경우 없는 경우 실행되도록 할 수 없을까? - Autowired의 required 속성을 false로 하면 가능
 * 
 * @Qualify : //Autowired할 떄 함수의 파라미터의 변수명이 다르고, 자료형이 같은 bean(객체)가 여러개 일 경우, 
 * 해당 id를 이용하여 binding할 수 있도록 함
 * 
 * @Configuration : xml파일을 자바 파일로 바꾸는 방법 -> 자바 클래스가 일반 클래스가 아니라 xml 설정을 위한 클래스다를 알려주기 위함.
 * 		@ComponentScan({"spring.di.ui", "spring.di.entity"})
 * 		@Bean -> 해당 객체를 spring의 ioc 컨테이너(프로그램에서 사용,공유할 수 있는 객체를 모아놓고 있는 컨테이너)에 담기게 한다.
 * 		public Exam exam() -> exam이 아이디 역할을 한다.'
 * 		return new NewlecExam()으로 class 설정 가능
 * 
 * 
 * 
 * *** AOP(Aspect Oriented Programming) - 관점 지향 프로그래밍 : 객체 지향 프로그래밍보다 약간 더 큰 틀, 사용자의 관점, 개발자의 관점, 운영자의 관점으로 나누어 프로그램을 만드는 형식
 * 		대표적으로 로그, 보안, 트랜잭션 처리등 개발자의 관점에서 필요한 코드들이 통상적으로 주 업무를 위, 아래로 감싸는 형태로 작성되는 경우가 많음
 * 		-> 이러한 개발자 관점 코드(Cross Cutting Concern)와 사용자 관점 코드(주업무, Core Concern)를 분리하는 것
 * 		proxy : 주업무를 갖고 있는 녀석을 호출해주는 역할 -> 사용자는 proxy를 호출
 * 
 * 		특정함수들만 proxy를 통해 위빙할 수 있도록 하려면
 * 		weaving(위빙) : proxy를 통해 위 아래로 aop를 적용시키는 것
 * 		JoinPoint : weaving에 대상이 되는 포인트, 기본적으로 전 함수로 설정
 * 		Pointcuts : JoinPoint를 나누어 특정함수에만 aop가 적용될 수 있도록 하는 것 - 이를 중간에서 연결해주는 녀석이 advisor
 * 
 * 
 * ========================================
 * 
 * *** SPRING MVC
 * tomcat : 웹 애플리케이션 서버로 웹서버와 연동하여 실행할 수 있는 자바 환경을 제공하여 jsp와 자바 서블릿이 실행할 수 있는 환경을 제공한다.
 * spring web : tomcat에서 spring을 사용하기 위해 spring Dispatcher를 장착시켜 tomcat에서 spring 환경으로 옮긴 것
 * 		- 이를 개발하는데 xml, annotation, java를 사용
 * spring boot : spring web을 개발할 때에 개발자들 간의 차이를 좁히기 위한 부가적인 기능을 제공, 스프링(Spring)을 더 쉽게 이용하기 위한 도구
 * */


public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//data entity class, entity를 활용하여 출력하는 class
		
		/* no spring - 스프링에게 지시하는 방법으로 코드 변경
		Exam exam = new NewlecExam();
		//ExamConsole console = new InlineExamconsole(exam); // DI - 부품 조립
		ExamConsole console = new GridExamConsole(); // 이녀석으로도 바꿀 수 있다.
		//이로 수정하는 것이 코드 설정없이 하게 하기 위해 외부로 뺀다. - 이를 spring의 도움을 받는다.
		
		console.setExam(exam);
		*/
		
		ApplicationContext context = 
		//		new ClassPathXmlApplicationContext("/spring/di/setting.xml");
				new AnnotationConfigApplicationContext(newlecDIConfig.class);
		//context.register(클래스명.class);를 이용하여 여러개의 configure 파일을 설정할 수도 있다. 마지막에 context.refresh()를 하면 된다.
		
		//Component를 사용할 경우 객체 id명으로 찾으면 에러가 발생 -> Component("id명")으로 설정가능
		ExamConsole console = (ExamConsole) context.getBean("console");
		//ExamConsole console = context.getBean(ExamConsole.class);
		
		console.print();
		
		
//		List<Exam> exams = (List<Exam>) context.getBean("exams");
//		//exams.add(new NewlecExam(1,1,1,1));
//		
//		
//		for (Exam e : exams)
//			System.out.println(e);

	}
}
