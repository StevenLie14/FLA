package cookstate;

import model.Cook;

public abstract class CookState {
	
	private Cook cook;

	public CookState(Cook cook) {
		super();
		this.cook = cook;
	}

	public Cook getCook() {
		return cook;
	}

	public void setCook(Cook cook) {
		this.cook = cook;
	}
	
	public abstract void updateState();
	public abstract String getState();
	
	
	

}
