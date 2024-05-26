package model;

import cookstate.CookState;
import cookstate.CookingState;
import cookstate.DoneState;
import cookstate.IdleState;
import cookstate.OrderState;
import facade.RestoFacade;
import mediator.RestoMediator;

public class Cook  extends Person{
	
	private Integer speed;
	private CookState state;
	private CookingState cooking;
	private DoneState done;
	private IdleState idle;
	private Integer skill;
	private OrderState order;
	
	public Cook(String name, Integer speed, Integer skill,RestoMediator mediator) {
		super(name,mediator);
		this.speed = speed;
		this.skill = skill;
		this.state = new IdleState(this);
		this.done = new DoneState(this);
		this.idle = new IdleState(this);
		this.cooking = new CookingState(this);
		this.order = new OrderState(this);
	}
	
	public CookState getState() {
		return state;
	}
	
	public void changeState(CookState state) {
		this.state = state;
		this.state.startState();
	}
	
	@Override
	public void run() {
		while(!RestoFacade.getInstance().isEnded()) {
			if(!RestoFacade.getInstance().isPaused()) {
				this.state.updateState();
			}
		}
	}
	
	public OrderState getOrder() {
		return order;
	}


	public void setOrder(OrderState order) {
		this.order = order;
	}


	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	
	public Integer getSkill() {
		return skill;
	}
	public void setSkill(Integer skill) {
		this.skill = skill;
	}
	
	public CookingState getCooking() {
		return cooking;
	}
	public void setCooking(CookingState cooking) {
		this.cooking = cooking;
	}
	public DoneState getDone() {
		return done;
	}
	public void setDone(DoneState done) {
		this.done = done;
	}
	public IdleState getIdle() {
		return idle;
	}
	public void setIdle(IdleState idle) {
		this.idle = idle;
	}
	public void setState(CookState state) {
		this.state = state;
	}
	
	
	
	
	
	
	

}
