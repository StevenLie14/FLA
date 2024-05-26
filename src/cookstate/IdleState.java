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
	public void startState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getState() {
		return "idle";
	}

	

}
