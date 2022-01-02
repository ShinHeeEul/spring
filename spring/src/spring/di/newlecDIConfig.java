package spring.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.di.entity.Exam;
import spring.di.entity.NewlecExam;

@ComponentScan({"spring,di,ui","spring.di.entity"})
@Configuration
public class newlecDIConfig {
	@Bean
	//반환 타입이 클래스(인터페이스), 함수 명이 id
	public Exam exam1() {
		return new NewlecExam(20,30,10,10);
	}
	
	@Bean
	public Exam exam2() {
		return new NewlecExam();
	}
}
