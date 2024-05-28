package model;

import customerstate.CustomerState;
import customerstate.EatState;
import customerstate.OrderState;
import customerstate.WaitingCookFoodState;
import customerstate.WaitingOrderSentState;
import customerstate.WaitingOrderState;
import customerstate.WaitingWaiterFoodState;
import facade.RestoFacade;
import mediator.RestoMediator;

public class Customer extends Person{
	
	private CustomerState state;
	private Integer tolerance;
	private EatState eat;
	private OrderState ordering;
	private WaitingOrderState callwaiter;
	private WaitingOrderSentState callchef;
	private WaitingCookFoodState chefcooking;
	private WaitingWaiterFoodState waiterfood;

	








	public Customer(String name, Integer tolerance, RestoMediator mediator) {
		super(name,mediator);
		this.state = new WaitingOrderState(this);
		this.tolerance = tolerance;
		this.eat = new EatState(this);
		this.ordering = new OrderState(this);
		this.callwaiter = new WaitingOrderState(this);
		this.callchef = new WaitingOrderSentState(this);
		this.chefcooking = new WaitingCookFoodState(this);
		this.waiterfood = new WaitingWaiterFoodState(this);
	}

	

	public CustomerState getState() {
		return state;
	}

	public void changeState(CustomerState state) {
		this.state = state;
	}

	public Integer getTolerance() {
		return tolerance;
	}

	public EatState getEat() {
		return eat;
	}




	public OrderState getOrdering() {
		return ordering;
	}



	public WaitingOrderSentState getCallchef() {
		return callchef;
	}






	public WaitingCookFoodState getChefcooking() {
		return chefcooking;
	}






	public WaitingWaiterFoodState getWaiterfood() {
		return waiterfood;
	}






	public void setState(CustomerState state) {
		this.state = state;
	}



	public void setTolerance(Integer tolerance) {
		this.tolerance = tolerance;
	}



	@Override
	public void run() {
		while(!RestoFacade.getInstance().isEnded()) {
			if(!RestoFacade.getInstance().isPaused()) {
				this.state.updateState();
			}
		}
	}
	
	
	

}
