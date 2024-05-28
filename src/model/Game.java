package model;

import facade.RestoFacade;

public class Game implements Runnable{

	
	

	public Game() {
		super();
	}

	@Override
	public void run() {
		while(!RestoFacade.getInstance().isEnded()) {
				RestoFacade.getInstance().GameStart();
		}
	}

}
