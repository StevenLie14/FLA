package main;

import java.util.Scanner;

import facade.RestoFacade;

public class Main {

	public Main() {
		RestoFacade.getInstance().StartMenu();
		
	}

	public static void main(String[] args) {
		new Main();
	}

}
