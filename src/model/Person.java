package model;

import mediator.RestoMediator;

public abstract class Person implements Runnable{

	private String name;
	private RestoMediator mediator;

	

	public RestoMediator getMediator() {
		return mediator;
	}

	public void setMediator(RestoMediator mediator) {
		this.mediator = mediator;
	}

	public Person(String name, RestoMediator mediator) {
		super();
		this.name = name;
		this.mediator = mediator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
