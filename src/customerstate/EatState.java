package customerstate;

import model.Cook;
import model.Customer;

public class EatState extends CustomerState{

	//(skill koki yang memasakkan makanan) * 30
	
	private Cook cook;
	public Cook getCook() {
		return cook;
	}

	public EatState setCook(Cook cook) {
		this.cook = cook;
		return this;
	}

	public EatState(Customer customer) {
		super(customer);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateState() {
		this.getCustomer().getMediator().goDie(getCustomer(), getCook());
	}

	
	
	public String getState() {
		return "eat";
	}


	
}
