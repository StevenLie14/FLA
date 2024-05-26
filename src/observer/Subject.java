package observer;

public interface Subject {

	public void setObserver (Observer obs);
	public void removerObserver (Observer obs);
	public void notifyObserver();
}
