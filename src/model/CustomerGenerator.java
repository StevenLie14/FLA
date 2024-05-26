package model;

import builder.FactoryBuilder;
import factory.CustomerFactory;
import factory.PersonFactory;
import mediator.RestoMediator;
import observer.Observer;

public class CustomerGenerator implements Observer{

	private RestoMediator mediator;
	
	

	public CustomerGenerator(RestoMediator mediator) {
		super();
		this.mediator = mediator;
	}



	@Override
	public void update() {
		
    	PersonFactory per = new CustomerFactory();
    	String name = this.mediator.getNewInitial();
    	FactoryBuilder fact = new FactoryBuilder().setName(name).setTolerance(5).setMediator(mediator);
    	Customer newcust = (Customer) per.createPerson(fact);
    	mediator.addUser(newcust);
		
	}
	
}
