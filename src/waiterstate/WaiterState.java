package waiterstate;

import model.Waiter;

public abstract class WaiterState {

	private Waiter waiter;

	
	
	public Waiter getWaiter() {
		return waiter;
	}



	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}



	public WaiterState(Waiter waiter) {
		super();
		this.waiter = waiter;
	}



	public abstract void updateState();
	public abstract void startState();
	public abstract String getState();

}
