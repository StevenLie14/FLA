package cookstate;

import model.Cook;
import model.Waiter;

public class OrderState extends CookState{

	private Waiter waiter;
	public Waiter getWaiter() {
		return waiter;
	}

	public OrderState setWaiter(Waiter waiter) {
		this.waiter = waiter;
		return this;
	}

	public OrderState(Cook cook) {
		super(cook);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateState() {
		
//		System.out.println("Order");
	}

	

	@Override
	public String getState() {
		return "order <"+this.getWaiter().getName()+ ">";
	}

	

}
