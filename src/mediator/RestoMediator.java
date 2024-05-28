package mediator;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

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

	private final Vector<Waiter> waiters;
	private final Vector<Cook> cooks;
	private final Vector<Customer> customers;
	private static final Random RANDOM = new Random();
	private final Vector<String> generatedCodes = new Vector<>();

	public RestoMediator() {
		super();
		waiters = new Vector<>();
		cooks = new Vector<>();
		customers = new Vector<>();
	}



	public  Vector<Waiter> getWaiters() {
		return waiters;
	}

	public   Vector<Cook> getCooks() {
		return cooks;
	}



	public  Vector<Customer> getCustomers() {
		return customers;
	}

	public  Vector<Waiter> gamegetWaiters() {
		return waiters;
	}

	public  Vector<Cook> gamegetCooks() {
		return cooks;
	}

	public  Vector<Customer> gamegetCustomers() {
		return customers;
	}


	public  void addUser(Waiter waiter) {
		waiters.add(waiter);
	}

	public void removeUser(Waiter waiter) {
		waiters.remove(waiter);
		generatedCodes.remove(waiter.getName());
	}

	public synchronized void addUser(Cook cook) {
		cooks.add(cook);
	}

	public void removeUser(Cook cook) {
		cooks.remove(cook);
		generatedCodes.remove(cook.getName());
	}

	public void addUser(Customer customer) {
		customers.add(customer);
	}

	public void removeUser(Customer customer) {
		customers.remove(customer);
	}


	public void addPoint(int point) {
		Restaurant.getInstance().setScore(Restaurant.getInstance().getScore() + point);
		Restaurant.getInstance().setMoney(Restaurant.getInstance().getMoney() + point);
	}

	public String getNewInitial() {
		String initial;
		do {
            char firstLetter = (char) ('A' + RANDOM.nextInt(26));
            char secondLetter = (char) ('A' + RANDOM.nextInt(26));
            initial = "" + firstLetter + secondLetter;
        } while (generatedCodes.contains(initial));
		generatedCodes.add(initial);
		return initial;
	}

//	 public synchronized void takeOrderCustomer(Waiter waiter) {
//		  Customer selectedCustomer = null;
//		   synchronized (customers) {
//			   for (Customer cust : customers) {
//
//				    if (cust.getState() instanceof WaitingOrderState) {
//					     selectedCustomer = cust;
//					     break;
//				    }
//			   }
//		  }
//
//		  if(selectedCustomer != null) {
//			   selectedCustomer.changeState(selectedCustomer.getOrdering().setWaiter(waiter));
//			   waiter.changeState(waiter.getTake().setCustomer(selectedCustomer));
//			   return;
//		  }
//
//
//
//
//
//
//
//		  return;
//	}

//	----------------------------------------------------------------------------------------------

	public void customerFindWaiter(Customer customer) {
		synchronized (waiters) {

			for (Waiter waiter : waiters) {
				if(waiter.getState() instanceof waiterstate.IdleState) {
					waiter.changeState(waiter.getTake().setCustomer(customer));
					customer.changeState(customer.getOrdering().setWaiter(waiter));
					break;
				}
			}
		}
	}

	public void bringOrdertoChef(Waiter waiter,Customer customer) {
		waiter.changeState(waiter.getBring().setCustomer(customer));
		customer.changeState(customer.getCallchef().setWaiter(waiter));
	}

	public void waiterFindCook(Waiter waiter) {
		synchronized (cooks) {
			   for (Cook cook : cooks) {
				    if (cook.getState() instanceof DoneState && waiter.getState() instanceof waiterstate.IdleState) {
					     Customer serveCustomer = ((DoneState) cook.getState()).getCustomer();
					     serveCustomer.changeState(serveCustomer.getWaiterfood().setWaiter(waiter));
						  waiter.changeState(waiter.getServe().setCustomer(serveCustomer).setCook(cook));
						  cook.changeState(cook.getIdle());
						  break;
				    }
			   }
		  }
	}

	public void waiterBringFood(Waiter waiter,Customer customer) {
		Cook idlecook = null;

		synchronized (cooks) {
			for (Cook cook : cooks) {
				if(cook.getState() instanceof DoneState) {

					Customer servecust = ((DoneState)cook.getState()).getCustomer();
					servecust.changeState(servecust.getWaiterfood().setWaiter(waiter));
					waiter.changeState(waiter.getServe().setCustomer(servecust).setCook(cook));
					cook.changeState(cook.getCooking().setCustomer(customer));
					return;
				}
				if(cook.getState() instanceof IdleState) {
					idlecook = cook;
				}
			}
		}

		try {
			if(idlecook != null) {
				Thread.sleep(1000);
				waiter.changeState(waiter.getIdle());
				idlecook.changeState(idlecook.getCooking().setCustomer(customer));
				customer.changeState(customer.getChefcooking().setCook(idlecook));
			}else {
				waiter.changeState(waiter.getCook().setCustomer(customer));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void serveOrder(Waiter waiter,Customer customer,Cook cook) {
		waiter.changeState(waiter.getIdle());
		customer.setState(customer.getEat().setCook(cook));
	}

	public void goDie(Customer customer,Cook cook) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for(int i = 0;i<100;i++) {
//			System.out.println("WOOEEOEDASHASHDSAH");
//		}
//		customer.changeState(customer.getDone());
		removeUser(customer);

	}






}
