package cookstate;

import model.Cook;

public class IdleState extends CookState {

	public IdleState(Cook cook) {
		super(cook);
	}

	@Override
	public void updateState() {
		
	}

	

	@Override
	public String getState() {
		return "idle";
	}

	

}
