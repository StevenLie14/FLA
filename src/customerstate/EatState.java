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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startState() {
		try {
			Thread.sleep(6000);	
//			System.out.println("s" + cook);
//			System.out.println("m" + this.getCustomer().getMediator());
			this.getCustomer().getMediator().addPoint(cook.getSkill()*30);
			this.getCustomer().getMediator().removeUser(this.getCustomer());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getState() {
		return "eat";
	}

	
}
