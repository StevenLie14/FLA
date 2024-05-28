package waiterstate;

import model.Customer;
import model.Waiter;

public class IdleState extends WaiterState{

	

	public IdleState(Waiter waiter) {
		super(waiter);
	}

	@Override
	public void updateState() {

			this.getWaiter().getMediator().waiterFindCook(getWaiter());
	}
	
	public String getState() {
		return "idle";
	}

	

	

}
