package customerstate;

import model.Customer;

public abstract class CustomerState {

	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerState(Customer customer) {
		super();
		this.customer = customer;
	}
	
	public abstract void updateState();
	public abstract String getState();

}
