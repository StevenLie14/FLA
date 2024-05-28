package waiterstate;

import model.Cook;
import model.Customer;
import model.Waiter;

public class BringOrderState extends WaiterState{

	private Customer customer;
	private Cook cook;
	private String type;
	
	



	public String getType() {
		return type;
	}



	public BringOrderState setType(String type) {
		this.type = type;
		return this;
	}



	public Cook getCook() {
		return cook;
	}



	public BringOrderState setCook(Cook cook) {
		this.cook = cook;
		return this;
	}



	public BringOrderState(Waiter waiter) {
		super(waiter);
	}
	
	

	public Customer getCustomer() {
		return customer;
	}


	public BringOrderState setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}



	@Override
	public void updateState() {
		
		this.getWaiter().getMediator().waiterBringFood(this.getWaiter(), this.getCustomer());
	}



	@Override
	public String getState() {
		return "bring order <" + this.getCustomer().getName() + ">";
	}



	

}
