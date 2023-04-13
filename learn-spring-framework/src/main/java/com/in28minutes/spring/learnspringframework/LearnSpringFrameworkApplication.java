package com.in28minutes.spring.learnspringframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.in28minutes.spring.learnspringframework.enterprise.example.MyWebController;
import com.in28minutes.spring.learnspringframework.game.GameRunner;


@SpringBootApplication
public class LearnSpringFrameworkApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LearnSpringFrameworkApplication.class, args);


		GameRunner gameRunner = context.getBean(GameRunner.class);
		gameRunner.run();

		MyWebController webController = context.getBean(MyWebController.class);

		System.out.println(webController.returnValueFromBusinessService());
	}

}
