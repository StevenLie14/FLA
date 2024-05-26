package restaurantstate;

import model.Restaurant;

public class FullState extends RestaurantState{

	public FullState(Restaurant resto) {
		super(resto);
	}

	@Override
	public void updateState() {
		
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return "FULL";
	}

	@Override
	public void startState() {
		// TODO Auto-generated method stub
		
	}

}
