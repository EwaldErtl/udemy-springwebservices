package com.in28minutes.spring.learnspringframework.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {

	@Autowired
	private GamingConsole game;

	public GameRunner(GamingConsole game) {
		System.out.println("Constructor injection");
		this.game = game;

	}



	public void run() {
		game.up();
		game.down();
		game.left();
		game.right();
	}



	public void setGame(GamingConsole game) {
		System.out.println("Setter Injection");
		this.game = game;
	}

}
