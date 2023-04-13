package com.in28minutes.spring.learnspringframework.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SupraContraGame implements GamingConsole {

	public void up() { 
		System.out.println("SupraContraGame: up");
	}
	public void down() { 
		System.out.println("SupraContraGame: down");
	}
	public void left() { 
		System.out.println("SupraContraGame: left");
	}
	public void right() { 
		System.out.println("SupraContraGame: right");
	}

}
