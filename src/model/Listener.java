package model;

import facade.RestoFacade;

public class Listener implements Runnable{

	
	

	public Listener() {
		super();
	}

	@Override
	public void run() {
		while(!RestoFacade.getInstance().isEnded()) {
			if(!RestoFacade.getInstance().isPaused()) {
				RestoFacade.getInstance().handleUserInput();
			}
		
		}
	}

}
