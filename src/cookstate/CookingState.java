package cookstate;

import model.Cook;
import model.Customer;

public class CookingState extends CookState{

	private Customer customer;
	
	public CookingState(Cook cook) {
		super(cook);
	}
	
	

	public Customer getCustomer() {
		return customer;
	}



	public CookingState setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}



//	@Override
//	public void updateState() {
//		// TODO Auto-generated method stub
//		System.out.println("cook me");
//	}



	@Override
	public void updateState() {
//		System.out.println("??");
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Thread.currentThread().interrupt();
		}
		if(this.getCook().getState() instanceof CookingState) {
//			for (int i = 0; i < 100; i++) {
//				System.out.println("DONE COOKING");
//			}
			this.getCook().changeState(this.getCook().getDone().setCustomer(this.getCustomer()));
		}
	
		
	}







	@Override
	public String getState() {
		return "cook <"+this.getCustomer().getName()+ ">";
		
	}



	
	
	

	

}
