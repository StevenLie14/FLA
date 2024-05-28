package waiterstate;

import model.Customer;
import model.Waiter;

public class WaitCookState extends WaiterState{

	
	private Customer customer;
	public WaitCookState(Waiter waiter) {
		super(waiter);
		// TODO Auto-generated constructor stub
	}
	
	

	public Customer getCustomer() {
		return customer;
	}



	public WaitCookState setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}



	@Override
	public void updateState() {
		this.getWaiter().getMediator().waiterBringFood(this.getWaiter(), this.getCustomer());
		
	}

	public String getState() {
		return "wait cook";
	}



	

}
