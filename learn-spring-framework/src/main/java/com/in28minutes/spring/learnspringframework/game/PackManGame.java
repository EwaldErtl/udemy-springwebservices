package com.in28minutes.spring.learnspringframework.game;

import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;

@Component
public class PackManGame implements GamingConsole {

	public void up() { 
		System.out.println("PackManGame: up");
	}
	public void down() { 
		System.out.println("PackManGame: down");
	}
	public void left() { 
		System.out.println("PackManGame: left");
	}
	public void right() { 
		System.out.println("PackManGame: right");
	}

}
