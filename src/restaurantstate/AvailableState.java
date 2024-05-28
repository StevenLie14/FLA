package restaurantstate;

import java.util.Random;

import model.Restaurant;

public class AvailableState extends RestaurantState {


	
	
	public AvailableState(Restaurant resto) {
		super(resto);
		
	}

	@Override
	public void updateState() {
		Random random = new Random();
		int chance = random.nextInt(100);
//		System.out.println(chance);
        if (chance < 25) {
        	Restaurant.getInstance().notifyObserver();
        }
		
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return "avail";
	}

	

}
