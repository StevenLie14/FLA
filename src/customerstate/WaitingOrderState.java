package customerstate;

import model.Customer;
import model.Restaurant;

public class WaitingOrderState extends CustomerState {

	public WaitingOrderState(Customer customer) {
		super(customer);
	}

	@Override
	public void updateState() {
		
		try {
			Thread.sleep(2000);	
			if(this.getCustomer().getState() instanceof WaitingOrderState == true) {
				if(this.getCustomer().getTolerance() <= 1) {
					Restaurant.getInstance().setScore(Restaurant.getInstance().getScore() -300);
					this.getCustomer().getMediator().removeUser(this.getCustomer());
					return;
					
				}
				this.getCustomer().setTolerance(this.getCustomer().getTolerance() - 1);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void startState() {
		// TODO Auto-generated method stub
		
	}
	
	public String getState() {
		return "order";
	}

	

}
