package customerstate;

import model.Customer;
import model.Waiter;

public class OrderState extends CustomerState {
	
	private Waiter waiter;
	
	public OrderState(Customer customer) {
		super(customer);
		this.waiter = null;
	}
	
	public Waiter getWaiter() {
		return waiter;
	}



	public OrderState setWaiter(Waiter waiter) {
		this.waiter = waiter;
		return this;
	}



	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startState() {
		// TODO Auto-generated method stub
		
	}
	
	public String getState() {
		return "order <" + this.getWaiter().getName() + ">";
	}

	

}
