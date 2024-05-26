package mediator;

import java.util.ArrayList;
import java.util.Random;

import cookstate.CookingState;
import cookstate.DoneState;
import cookstate.IdleState;
import customerstate.OrderState;
import customerstate.WaitingOrderState;
import model.Cook;
import model.Customer;
import model.Restaurant;
import model.Waiter;
import waiterstate.ServingFoodState;
import waiterstate.TakeOrderState;


public class RestoMediator {

//	private static RestoMediator instance = null;
//	public synchronized static RestoMediator getInstance() {
//		if (instance == null){
//			instance = new RestoMediator();
//		}
//		return instance;
//	}
	
	private ArrayList<Waiter> waiters;
	private ArrayList<Cook> cooks;
	private ArrayList<Customer> customers;
	private static final Random RANDOM = new Random();
	private final ArrayList<String> generatedCodes = new ArrayList<>();
	
	public RestoMediator() {
		super();
		waiters = new ArrayList<>();
		cooks = new ArrayList<>();
		customers = new ArrayList<>();
	}
	
	public  ArrayList<Waiter> getWaiters() {
		return waiters;
	}

	public   ArrayList<Cook> getCooks() {
		return cooks;
	}

	

	public  ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	public ArrayList<Waiter> gamegetWaiters() {
		return waiters;
	}

	public  ArrayList<Cook> gamegetCooks() {
		return cooks;
	}

	

	public ArrayList<Customer> gamegetCustomers() {
		return customers;
	}


	public synchronized void addUser(Waiter waiter) {
		waiters.add(waiter);
//		Thread t1 = new Thread(waiter);
//		t1.start();
	}
	
	public synchronized void removeUser(Waiter waiter) {
		waiters.remove(waiter);
		generatedCodes.remove(waiter.getName());
	}
	
	public synchronized void addUser(Cook cook) {
		cooks.add(cook);
	}
	
	public synchronized void removeUser(Cook cook) {
		cooks.remove(cook);
		generatedCodes.remove(cook.getName());
	}
	
	public void addUser(Customer customer) {
		customers.add(customer);
	}
	
	public synchronized void removeUser(Customer customer) {
		customers.remove(customer);
		
//		generatedCodes.remove(customer.getName());
	}

	
	public synchronized void addPoint(int point) {
		
		Restaurant.getInstance().setScore(Restaurant.getInstance().getScore() + point);
		Restaurant.getInstance().setMoney(Restaurant.getInstance().getMoney() + point);
	}
	
	public String getNewInitial() {
		String initial;
		do {
            char firstLetter = (char) ('A' + RANDOM.nextInt(26));
            char secondLetter = (char) ('A' + RANDOM.nextInt(26));
//            System.out.println("???");
            initial = "" + firstLetter + secondLetter;
        } while (generatedCodes.contains(initial));
		generatedCodes.add(initial);

		return initial;
	}
	
	public synchronized void takeOrderCustomer(Waiter waiter) {
            for (Customer cust : getCustomers()) {
                    if (cust.getState() instanceof WaitingOrderState) {
                        cust.changeState(cust.getOrdering().setWaiter(waiter));
                        waiter.changeState(waiter.getTake().setCustomer(cust));
                        return;
                    }
            }

            for (Cook cook : getCooks()) {
                    if (cook.getState() instanceof DoneState) {
                        Customer servecust = ((DoneState) cook.getState()).getCustomer();
                        synchronized (servecust) {
                            servecust.changeState(servecust.getWaiterfood().setWaiter(waiter));
                            waiter.changeState(waiter.getServe().setCustomer(servecust).setCook(cook));
                            cook.changeState(cook.getIdle());
                            return;
                        }
                    }
                }
    }
	
	public synchronized void bringOrdertoChef(Waiter waiter,Customer customer) {
		waiter.changeState(waiter.getBring().setCustomer(customer));
		customer.changeState(customer.getCallchef().setWaiter(waiter));
	}
	
	public synchronized void getCooker(Waiter waiter,Customer customer) {
		Cook donecook = null;
		Cook idlecook = null;
			
		for (Cook cook : getCooks()) {
			if(cook.getState() instanceof DoneState == true) {
				donecook = cook;
				break;
			}
			if(cook.getState() instanceof IdleState == true) {
				idlecook = cook;
			}
		}
		
		try {
			if(donecook != null) {
//				waiter.changeState(waiter.getBring().setCustomer(customer).setCook(donecook).setType("done"));
//				customer.changeState(customer.getChefcooking().setCook(donecook));
//				donecook.changeState(donecook.getOrder().setWaiter(waiter));
				waiter.setState(waiter.getBring());
				Thread.sleep(1000);
				Customer servecust = ((DoneState)donecook.getState()).getCustomer();
				servecust.changeState(servecust.getWaiterfood().setWaiter(waiter));
				waiter.changeState(waiter.getServe().setCustomer(servecust).setCook(donecook));
				donecook.changeState(donecook.getCooking().setCustomer(customer));
				customer.changeState(customer.getChefcooking().setCook(donecook));
			}else if(idlecook != null) {
//				waiter.changeState(waiter.getBring().setCustomer(customer).setCook(idlecook).setType("idle"));
//				customer.changeState(customer.getChefcooking().setCook(idlecook));
				waiter.setState(waiter.getBring());
//				idlecook.changeState(idlecook.getOrder().setWaiter(waiter));
				Thread.sleep(1000);
				waiter.changeState(waiter.getIdle());
				idlecook.changeState(idlecook.getCooking().setCustomer(customer));
				customer.changeState(customer.getChefcooking().setCook(idlecook));
			}else if(donecook == null && idlecook == null) {
				waiter.changeState(waiter.getCook().setCustomer(customer));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public synchronized void waiterBringOrder(Waiter waiter,Customer customer,Cook cook,String type) {
//		try {
//			Thread.sleep(1000);
//			waiter.changeState(waiter.getIdle());
//			customer.changeState(customer.getWaiterfood().setWaiter(waiter));
////				cook.changeState(cook.getCooking().setCustomer(customer));
////				waiter.changeState(waiter.getServe().setCustomer(customer).setCook(cook));
////				customer.changeState(customer.getWaiterfood().setWaiter(waiter));
////				cook.changeState(cook.getCooking().setCustomer(customer));
//			
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public synchronized void serveOrder(Waiter waiter,Customer customer,Cook cook) {
		try {
			Thread.sleep(1000);
			customer.changeState(customer.getEat().setCook(cook));
			waiter.changeState(waiter.getIdle());
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public synchronized Cook getIdleCooker() {
		for (Cook cook : cooks) {
			if(cook.getState() instanceof IdleState == true) {
				return cook;
			}
		}
		return null;
	}
	
	public synchronized Cook getIdleorDoneCooker() {
		for (Cook cook : cooks) {
			if(cook.getState() instanceof IdleState == true || cook.getState() instanceof DoneState == true) {
				return cook;
			}
		}
		return null;
	}
	
	

}
