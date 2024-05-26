package waiterstate;

import model.Customer;
import model.Waiter;

public class IdleState extends WaiterState{

	

	public IdleState(Waiter waiter) {
		super(waiter);
	}

	@Override
	public void updateState() {
//		for(int i = 0;i<100;i++) {
//			System.out.println(this.getWaiter().getName() + " is idle, checking for tasks...");
//		}
//		System.out.println(this.getWaiter().getName());
//		if(this.getWaiter().getMediator().getCustomers().size() > 0) {
			this.getWaiter().getMediator().takeOrderCustomer(this.getWaiter());
//		}
	}
	
	public String getState() {
		return "idle";
	}

	@Override
	public void startState() {
		// TODO Auto-generated method stub
		
	}

	

}
