package spring.di.ui;

import spring.di.entity.Exam;

public class InlineExamconsole implements ExamConsole {

	private Exam exam;
	public InlineExamconsole() {
		// TODO Auto-generated constructor stub
	}
	public InlineExamconsole(Exam exam) {
		super();
		this.exam = exam;
	}


	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.printf("total is %d, avg is %f\n", exam.total(), exam.avg());
	}
	@Override
	public void setExam(Exam exam) {
		// TODO Auto-generated method stub
		this.exam = exam;
	}

}
