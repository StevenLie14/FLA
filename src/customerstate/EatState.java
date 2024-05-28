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
		// TODO Auto-generated method stub
//				try {
////
////					synchronized (this.getCustomer()) {
////		                System.out.println("Changing state to Done and adding points...");
////		                this.getCustomer().getMediator().addPoint(cook.getSkill() * 30);
////		                System.out.println("State changed and points added.");
////		            }
////					System.out.println("s" + cook);
////					System.out.println("m" + this.getCustomer().getMediator());
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
	}

	
	
	public String getState() {
		return "eat";
	}


	
}
