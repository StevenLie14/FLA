package customerstate;

import model.Cook;
import model.Customer;
import model.Restaurant;

public class WaitingCookFoodState extends CustomerState{

	private Cook cook;

	public WaitingCookFoodState(Customer customer) {
		super(customer);
		// TODO Auto-generated constructor stub
	}



	public Cook getCook() {
		return cook;
	}



	public WaitingCookFoodState setCook(Cook cook) {
		this.cook = cook;
		return this;
	}



	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(4000);
			if(this.getCustomer().getState() instanceof WaitingCookFoodState) {
				if(this.getCustomer().getTolerance() <= 1) {
					Restaurant.getInstance().setScore(Restaurant.getInstance().getScore() -300);
					this.getCustomer().getMediator().removeUser(this.getCustomer());
					this.getCook().changeState(this.getCook().getIdle());
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
		return "wait food <" + this.getCook().getName() + ">";
	}







}
