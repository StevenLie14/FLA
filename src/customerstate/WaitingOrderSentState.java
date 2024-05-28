package customerstate;

import model.Customer;
import model.Restaurant;
import model.Waiter;

public class WaitingOrderSentState extends CustomerState {

	private Waiter waiter;

	public Waiter getWaiter() {
		return waiter;
	}

	public WaitingOrderSentState setWaiter(Waiter waiter) {
		this.waiter = waiter;
		return this;
	}

	public WaitingOrderSentState(Customer customer) {
		super(customer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateState() {
		try {
			Thread.sleep(4000);
			if(this.getCustomer().getState() instanceof WaitingOrderSentState == true) {
				if(this.getCustomer().getTolerance() <= 1) {
					Restaurant.getInstance().setScore(Restaurant.getInstance().getScore() -300);
					this.getCustomer().getMediator().removeUser(this.getCustomer());
					this.getWaiter().changeState(this.getWaiter().getIdle());
					return;
				}
				this.getCustomer().setTolerance(this.getCustomer().getTolerance() - 1);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public String getState() {
		return "wait food";
	}





}
