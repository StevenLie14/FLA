package model;

import builder.FactoryBuilder;
import facade.RestoFacade;
import factory.CookFactory;
import factory.CustomerFactory;
import factory.PersonFactory;
import factory.WaiterFactory;
import mediator.RestoMediator;
import observer.Observer;
import observer.Subject;
import restaurantstate.AvailableState;
import restaurantstate.FullState;
import restaurantstate.RestaurantState;

public class Restaurant implements Subject, Runnable{

	private Integer seat;
	private Integer money;
	private Integer score;
	private Observer observer;
	private RestaurantState state;
	private AvailableState avail;
	private FullState full;
	private RestoMediator mediator;
	
	public AvailableState getAvail() {
		return avail;
	}



	public void setAvail(AvailableState avail) {
		this.avail = avail;
	}



	public FullState getFull() {
		return full;
	}



	public void setFull(FullState full) {
		this.full = full;
	}



	public RestoMediator getMediator() {
		return mediator;
	}



	public void setMediator(RestoMediator mediator) {
		this.mediator = mediator;
	}



	private static Restaurant instance;
	
	public static synchronized Restaurant getInstance() {
		if(instance == null) {
			instance = new Restaurant();
			Thread t = new Thread(instance);
			t.start();
		}
		return instance;
	}
	
	
	
	@Override
	public void removerObserver(Observer obs) {
		observer = null;
		
	}

	@Override
	public void notifyObserver() {
		observer.update();
		
	}
	
	public Observer getObserver() {
		return observer;
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}

	public RestaurantState getState() {
		return state;
	}

	public void setState(RestaurantState state) {
		this.state = state;
		this.state.startState();
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	

	public Restaurant() {
		super();
		this.seat = 4;
		this.money = 1300;
		this.score = 0;
		this.avail = new AvailableState(this);
		this.full = new FullState(this);
		this.state = new AvailableState(this);
	}



	@Override
	public void run() {
		while(!RestoFacade.getInstance().isEnded()) {
			if(!RestoFacade.getInstance().isPaused()) {
				try {
					Thread.sleep(1000);
					if(Restaurant.getInstance().getMediator().gamegetCustomers().size() < seat) {

						Restaurant.getInstance().setState(Restaurant.getInstance().getAvail());
					}else {

						Restaurant.getInstance().setState(Restaurant.getInstance().getFull());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Restaurant.getInstance().state.updateState();
			}
		}
			
			
		
	}
	

	

	
	

}
