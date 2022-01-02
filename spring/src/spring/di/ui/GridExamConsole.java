package spring.di.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import spring.di.entity.Exam;

@Component("console")
public class GridExamConsole implements ExamConsole {

	@Autowired(required = false) // 자동으로 객체를 연결해달라는 의미
	//기본 생성자를 호출하면서 binding 됨
	@Qualifier("exam2") 
	private Exam exam;
	
	//@Autowired // 자동으로 객체를 연결해달라는 의미
	//기본 생성자 앞에서도 사용가능하네? 근데 Qualifier는 사용 불가 - 여러가지 파라미터가 설정가능하므로 특정 변수를 찝어 사용하기 어렵다 -> 오버로드 생성자 참고
	
	
	public GridExamConsole() {
		// TODO Auto-generated constructor stub
		System.out.println("constructor");
	}
	
	public GridExamConsole(@Qualifier("exam1") Exam exam1/*이런 식으로도 사용가능*/, Exam exam2) {
		this.exam = exam;
		System.out.println("overloaded constructor");
	}
	@Override
	public void print() {
		// TODO Auto-generated method stub
		if (exam == null) {
			System.out.println("┌─────────┬─────────┐");
			System.out.println("│  total  │   avg   │");
			System.out.println("├─────────┼─────────┤");
			System.out.printf("│   %3d   │  %3.2f   │\n", 0, 0.0f);
			System.out.println("└─────────┴─────────┘");
		} else {
			System.out.println("┌─────────┬─────────┐");
			System.out.println("│  total  │   avg   │");
			System.out.println("├─────────┼─────────┤");
			System.out.printf("│   %3d   │  %3.2f   │\n", exam.total(), exam.avg());
			System.out.println("└─────────┴─────────┘");
		

		}
	}

	
	// @Autowired // 자동으로 객체를 연결해달라는 의미
	//Autowired할 떄 함수의 파라미터의 변수명이 다르고, 자료형이 같은 bean(객체)가 여러개 일 경우, 해당 id를 이용하여 binding할 수 있도록 함 
	// @Qualifier("exam1")
	 	
	@Override
	public void setExam(Exam exam) {
		// TODO Auto-generated method stub
		this.exam = exam;
		System.out.println("setter constructor");
	}

}
