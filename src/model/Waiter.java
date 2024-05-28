package model;

import facade.RestoFacade;
import mediator.RestoMediator;
import waiterstate.BringOrderState;
import waiterstate.IdleState;
import waiterstate.ServingFoodState;
import waiterstate.TakeOrderState;
import waiterstate.WaitCookState;
import waiterstate.WaiterState;

public class Waiter extends Person {

	private WaiterState state;
	private BringOrderState bring;
	private IdleState idle;
	private ServingFoodState serve;
	private TakeOrderState take;
	private WaitCookState cook;
	private Integer speed;
	
	public Waiter(String name, Integer speed,RestoMediator mediator) {
		super(name,mediator);
		this.state = new IdleState(this);
		this.speed = speed;
		this.bring = new BringOrderState(this);
		this.idle = new IdleState(this);
		this.serve = new ServingFoodState(this);
		this.take = new TakeOrderState(this);
		this.cook = new WaitCookState(this);
	}
	

	public BringOrderState getBring() {
		return bring;
	}

	public void setBring(BringOrderState bring) {
		this.bring = bring;
	}

	public IdleState getIdle() {
		return idle;
	}

	public void setIdle(IdleState idle) {
		this.idle = idle;
	}

	public ServingFoodState getServe() {
		return serve;
	}

	public void setServe(ServingFoodState serve) {
		this.serve = serve;
	}

	public TakeOrderState getTake() {
		return take;
	}

	public void setTake(TakeOrderState take) {
		this.take = take;
	}

	public WaitCookState getCook() {
		return cook;
	}

	public void setCook(WaitCookState cook) {
		this.cook = cook;
	}
	
	public WaiterState getState() {
		return state;
	}
	
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	@Override
	public void run() {
		while(!RestoFacade.getInstance().isEnded()) {
			if(!RestoFacade.getInstance().isPaused()) {
				this.state.updateState();
			}
		}
	}
	
	public void changeState(WaiterState state) {
		this.state = state;
//		this.state.startState();
	}
	
	public void setState(WaiterState state) {
		this.state = state;
	}
	
	
	

}
