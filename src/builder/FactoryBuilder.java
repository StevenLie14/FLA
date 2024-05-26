package builder;

import mediator.RestoMediator;
import model.Cook;
import model.Customer;
import model.Waiter;

public class FactoryBuilder {

	private String name;
	private Integer skill;
	private Integer tolerance;
	private Integer speed;
	private RestoMediator mediator;
	
	public FactoryBuilder setName (String name) { this.name = name; return this; }
	public FactoryBuilder setSkill(Integer skill) { this.skill = skill; return this; }
	public FactoryBuilder setTolerance(Integer tolerance) { this.tolerance = tolerance; return this; }
	public FactoryBuilder setSpeed(Integer speed) { this.speed = speed; return this; }
	public FactoryBuilder setMediator(RestoMediator mediator) { this.mediator = mediator; return this; }
	
	private void cleanUp() {
		this.name = null;
		this.skill = null;
		this.tolerance = null;
		this.speed = null;
		this.mediator = null;
	}
	public Customer buildCustomer() {
		Customer cust = new Customer(name, tolerance,mediator);
		new Thread(cust).start();;
		this.cleanUp();
	    return cust;
	}	
	
	public Cook buildCook() {
		Cook cook = new Cook(name, speed, skill,mediator);
		new Thread(cook).start();
		this.cleanUp();
	    return cook;
	}	
	
	public Waiter buildWaiter() {
		Waiter waiter = new Waiter(name, speed, mediator);
		new Thread(waiter).start();
		this.cleanUp();
	    return waiter;
	}	

}
