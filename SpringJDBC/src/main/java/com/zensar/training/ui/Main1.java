package com.zensar.training.ui;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.zensar.training")
public class Main1 {
	public static void main(String[] args) {
		while(true) {
			MenuHandler handler=new MenuHandler();
			handler.displayMenu();
		}
	}
}
