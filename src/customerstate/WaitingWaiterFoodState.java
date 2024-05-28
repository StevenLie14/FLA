package customerstate;

import model.Customer;
import model.Restaurant;
import model.Waiter;

public class WaitingWaiterFoodState extends CustomerState{

	public Waiter waiter;

	public Waiter getWaiter() {
		return waiter;
	}

	public WaitingWaiterFoodState setWaiter(Waiter waiter) {
		this.waiter = waiter;
		return this;
	}

	public WaitingWaiterFoodState(Customer customer) {
		super(customer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateState() {
		try {
			Thread.sleep(4000);
			if(this.getCustomer().getState() instanceof WaitingWaiterFoodState == true) {
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
		return "wait food <" + this.getWaiter().getName() + ">";
	}




}
