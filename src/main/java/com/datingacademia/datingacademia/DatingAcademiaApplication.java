package com.datingacademia.datingacademia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) //스프링 시큐리티에서 자동으로 생성하는 로그인페이지 제거
public class DatingAcademiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatingAcademiaApplication.class, args);
		System.out.println("ADass");
	}

}
