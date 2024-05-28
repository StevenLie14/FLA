package waiterstate;

import model.Cook;
import model.Customer;
import model.Waiter;

public class ServingFoodState extends WaiterState{

	private Customer customer;
	private Cook cook;

	public Cook getCook() {
		return cook;
	}



	public ServingFoodState setCook(Cook cook) {
		this.cook = cook;
		return this;
	}



	public ServingFoodState(Waiter waiter) {
		super(waiter);
		// TODO Auto-generated constructor stub
	}
	
	

	public Customer getCustomer() {
		return customer;
	}



	public ServingFoodState setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}



	@Override
	public void updateState() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getWaiter().getMediator().serveOrder(getWaiter(), customer,cook);
		
		
	}
	
	public String getState() {
		return "serve food <" + this.getCustomer().getName() + ">";
	}



	
	
	

	

}
