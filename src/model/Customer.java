package model;

import customerstate.CustomerState;
import customerstate.DoneState;
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
	private DoneState done;

	



	public DoneState getDone() {
		return done;
	}



	public void setDone(DoneState done) {
		this.done = done;
	}



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
		this.done = new DoneState(this);
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



	public void setEat(EatState eat) {
		this.eat = eat;
	}



	public OrderState getOrdering() {
		return ordering;
	}



	public void setOrdering(OrderState ordering) {
		this.ordering = ordering;
	}



	public WaitingOrderState getCallwaiter() {
		return callwaiter;
	}



	public void setCallwaiter(WaitingOrderState callwaiter) {
		this.callwaiter = callwaiter;
	}



	public WaitingOrderSentState getCallchef() {
		return callchef;
	}



	public void setCallchef(WaitingOrderSentState callchef) {
		this.callchef = callchef;
	}



	public WaitingCookFoodState getChefcooking() {
		return chefcooking;
	}



	public void setChefcooking(WaitingCookFoodState chefcooking) {
		this.chefcooking = chefcooking;
	}



	public WaitingWaiterFoodState getWaiterfood() {
		return waiterfood;
	}



	public void setWaiterfood(WaitingWaiterFoodState waiterfood) {
		this.waiterfood = waiterfood;
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
