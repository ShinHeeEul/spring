package spring.di;

import spring.di.entity.Exam;
import spring.di.entity.NewlecExam;
import spring.di.ui.ExamConsole;
import spring.di.ui.GridExamConsole;
import spring.di.ui.InlineExamconsole;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//data entity class, entity를 활용하여 출력하는 class
		
		Exam exam = new NewlecExam();
		//ExamConsole console = new InlineExamconsole(exam); // DI - 부품 조립
		ExamConsole console = new GridExamConsole(exam); // 이녀석으로도 바꿀 수 있다.
		//이로 수정하는 것이 코드 설정없이 하게 하기 위해 외부로 뺀다. - 이를 spring의 도움을 받는다.
		console.setExam(exam);
		console.print();
	}

}
