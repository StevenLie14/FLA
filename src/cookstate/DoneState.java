package cookstate;

import model.Cook;
import model.Customer;

public class DoneState extends CookState{

	private Customer customer;
	
	public DoneState(Cook cook) {
		super(cook);
		// TODO Auto-generated constructor stub
	}

	public Customer getCustomer() {
		return customer;
	}

	public DoneState setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}

	@Override
	public void updateState() {
		
		
	}

	@Override
	public void startState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return "done <"+this.getCustomer().getName()+ ">";
	}

	

}
