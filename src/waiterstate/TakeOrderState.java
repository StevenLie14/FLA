package waiterstate;

import model.Customer;
import model.Waiter;

public class TakeOrderState extends WaiterState{

	private Customer customer;

	public TakeOrderState(Waiter waiter) {
		super(waiter);
	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(6000 - this.getWaiter().getSpeed()*1000);
			this.getWaiter().getMediator().bringOrdertoChef(getWaiter(), customer);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public TakeOrderState setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}

	public String getState() {
		return "take order <" + this.getCustomer().getName() + ">";
	}

	

}
