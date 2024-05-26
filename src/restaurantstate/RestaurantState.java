package restaurantstate;

import mediator.RestoMediator;
import model.Restaurant;

public abstract class RestaurantState {

	private Restaurant resto;
	

	public RestaurantState(Restaurant resto) {
		super();
		this.resto = resto;
	}
	
	
	
	public Restaurant getResto() {
		return resto;
	}



	public void setResto(Restaurant resto) {
		this.resto = resto;
	}



	public abstract void updateState();
	public abstract void startState();
	public abstract String getState();

}
