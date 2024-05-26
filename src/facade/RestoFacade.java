package facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import builder.FactoryBuilder;
import factory.CookFactory;
import factory.PersonFactory;
import factory.WaiterFactory;
import mediator.RestoMediator;
import model.Cook;
import model.Customer;
import model.CustomerGenerator;
import model.Game;
import model.Listener;
import model.Restaurant;
import model.Waiter;
import singleton.HighScore;

public class RestoFacade {
	
	private RestoMediator mediator;
	private CustomerGenerator gen;
	private String restoname;
	private boolean isPaused;
	private boolean isEnded;
	
	public void ToogleGame() {
		this.isPaused = !isPaused;
	}
	
	public void EndGame () {
		isEnded = false;
	}
	
	private static RestoFacade instance;
	
	
	public static RestoFacade getInstance(){
		if(instance == null) {
			instance = new RestoFacade();
		}
		return instance;
	}
	
	
	
	public RestoFacade() {
		super();
		this.mediator = new RestoMediator();
		this.gen = new CustomerGenerator(mediator);
		this.isEnded = false;
		this.isPaused= false;
	}
	
	public void initPerson() {
		PersonFactory per = new WaiterFactory();
    	FactoryBuilder fact ;
    	fact = new FactoryBuilder().setName(mediator.getNewInitial()).setSpeed(1).setMediator(mediator);
    	Waiter waiter1 = (Waiter) per.createPerson(fact);
    	fact = new FactoryBuilder().setName(mediator.getNewInitial()).setSpeed(1).setMediator(mediator);
    	Waiter waiter2 = (Waiter) per.createPerson(fact);
		mediator.addUser(waiter1);
		mediator.addUser(waiter2);
		per = new CookFactory();
    	fact = new FactoryBuilder().setName(mediator.getNewInitial()).setSpeed(1).setSkill(25).setMediator(mediator);
    	Cook cook1 = (Cook) per.createPerson(fact);
    	fact = new FactoryBuilder().setName(mediator.getNewInitial()).setSpeed(1).setSkill(25).setMediator(mediator);
    	Cook cook2 = (Cook) per.createPerson(fact);
		mediator.addUser(cook1);
		mediator.addUser(cook2);
	}

	public void PauseMenu() {
		Scanner scanf = new Scanner(System.in);;
	    int choice = 0;
	    do {
	        choice = 0;
	        System.out.println("1. Continue Business");
	        System.out.println("2. Upgrade Business");
	        System.out.println("3. Close Business");
	        System.out.print("Input [1..3] : ");
	        scanf.next();
	        String input = scanf.nextLine();
	        try {
	        choice = Integer.parseInt(input);
		        switch (choice) {
		            case 1:
		                ToogleGame();
		                break;
		            case 2:
		                UpgradeMenu();
		                break;
		            case 3:
		                break;
		            default:
		                System.out.println("Invalid choice. Please enter a valid option.");
		                break;
		        }
		    } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a number.");
	        }
	    } while (choice != 3 && choice != 1);
	}
	
	
	
	public void GameStart() {
		while(!RestoFacade.getInstance().isEnded) {
			
			if(!isPaused) {
				
				System.out.printf("%50s",this.restoname);
				RestoStatus();
				HighScore.getInstance().printEquals(90);
				System.out.printf("%30s | %30s | %30s %n","Customer","Waiter","Cook");
				HighScore.getInstance().printHyphens(90);
				System.out.println("Waiters");
				for (Waiter a : this.mediator.gamegetWaiters()) {
					System.out.println(a.getName()+" " +a.getState().getState());
				}
				System.out.println("Cooks");
				for (Cook a : this.mediator.gamegetCooks()) {
					System.out.println(a.getName()+ " " + a.getState().getState());
				}
				System.out.println("Customers");
				for (Customer a : this.mediator.gamegetCustomers()) {
					System.out.println(a.getName()+" " +a.getState().getState());
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Press Enter to pause/unpause...");
				cls();
				
			}else {
				PauseMenu();
			}
			
		}
	}
	
	public void handleUserInput() {
       
        Scanner scanner = new Scanner(System.in);
        try {
            while (!this.isPaused) {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    ToogleGame();
                }
            }
        } finally {
            scanner.close();
        }
    }
	
	public void RestoStatus() {
		System.out.printf("%10s %n","Status");
		System.out.printf("%20s %n","Money : " + Restaurant.getInstance().getMoney());
		System.out.printf("%20s %n","Score : " + Restaurant.getInstance().getScore());
		System.out.printf("%20s %n","Size  : " + Restaurant.getInstance().getSeat());
		System.out.printf("%20s %n","Available  : " + Restaurant.getInstance().getState().getState());
		System.out.printf("%20s %n","Seat avail  : " + Restaurant.getInstance().getMediator().gamegetCustomers().size());
		
	}
	
	public void StartMenu() {
		Scanner scanf = new Scanner(System.in);;
		int choice = 0;
		do {
			choice = 0;
			System.out.println("1. Play New Restaurant");
			System.out.println("2. High Score");
			System.out.println("3. Exit");
			System.out.print("Input [1..3] : ");
			choice = scanf.nextInt();
			switch (choice) {
			case 1:
				scanf.nextLine();
				do {
					System.out.print("Resto Name : ");
					restoname = scanf.nextLine();
				} while (restoname.length() <3 || restoname.length() > 15);
				Restaurant.getInstance().setObserver(gen);
//				System.out.println(Restaurant.getInstance().getObserver());
				Restaurant.getInstance().setMediator(mediator);
				initPerson();
				new Thread(new Game()).start();
//				new Thread(new Listener()).start();
				this.handleUserInput();
				
				break;
			case 2:
				HighScore.getInstance().ReadFile();
				break;
			case 3:
				System.out.println("BYE !");
				break;
			}
		} while (choice != 3 && choice !=1);
	}
	
	public boolean isPaused() {
		return isPaused;
	}

	

	public boolean isEnded() {
		return isEnded;
	}

	

	public void UpgradeMenu() {
		Scanner scanf = new Scanner(System.in);;
		int choice = 0;
		do {
			choice = 0;
			RestoStatus();
			System.out.println("1. Increase Restaurant's Seat <Rp. " + Restaurant.getInstance().getSeat() * 100 + ">" );
			System.out.println("2. Hire New Employee");
			System.out.println("3. Upgrade Waiter <Rp. 150>");
			System.out.println("4. Upgrade Cook <Rp. 150>");
			System.out.println("5. Back to Pause Menu>");
			System.out.print("Input [1..5] : ");
			choice = scanf.nextInt();
			switch (choice) {
			case 1:
				IncreaseResto();
				break;
			case 2:
				HireNewEmployee();
				break;
			case 3:
				UpgradeWaiter();
				break;
			case 4:
				UpgradeCooks();
				break;
			case 5:
				PauseMenu();
				break;
			}
		} while (choice != 5);
		
	}
	
	public void IncreaseResto() {
		if(Restaurant.getInstance().getSeat()< 13) {
			if(Restaurant.getInstance().getMoney() >= Restaurant.getInstance().getSeat() *100) {
				Restaurant.getInstance().setMoney(Restaurant.getInstance().getMoney() - Restaurant.getInstance().getSeat() *100);
				Restaurant.getInstance().setSeat(Restaurant.getInstance().getSeat()+1);
			}else {
				System.out.println("Money Not Enough");
			}
		}else {
			System.out.println("Cooker reached maximal");
		}
	}
	
	public void HireNewEmployee() {
		Scanner scanf = new Scanner(System.in);;
		int choice = 0;
		do {
			choice = 0;
			System.out.println("     Status     ");
			System.out.println("Money : Rp. " + Restaurant.getInstance().getMoney());
			System.out.println("Score : Rp. " + Restaurant.getInstance().getScore());
			System.out.println("Size : Rp. " + Restaurant.getInstance().getSeat());
			System.out.println("Hire New Employee");
			System.out.println("1. Hire New Waiter <Rp. " + mediator.getWaiters().size() * 150 + ">");
			System.out.println("2. Hire New Cook <Rp. " + mediator.getCooks().size() * 200 + ">");
			System.out.println("3. Back to Upgrade Menu>");
			System.out.print("Input [1..5] : ");
			choice = scanf.nextInt();
			FactoryBuilder builder = new FactoryBuilder();
			PersonFactory factory;
			switch (choice) {
			case 1:
				if(Restaurant.getInstance().getMoney() >= mediator.getWaiters().size() * 150) {
					if(mediator.getWaiters().size() < 7) {
						Restaurant.getInstance().setMoney(Restaurant.getInstance().getMoney()- mediator.getWaiters().size() * 150);
						factory = new WaiterFactory();
						Waiter waiter = builder.setMediator(mediator).setName(mediator.getNewInitial()).setSpeed(1).buildWaiter();
						mediator.addUser(waiter);
					}else {
						System.out.println("Cooker reached maximal");
					}
				}else {
					System.out.println("Money Not Enough");
				}
				break;
			case 2:
				if(Restaurant.getInstance().getMoney() >= Restaurant.getInstance().getMoney()-mediator.getCooks().size() * 200) {
					if(mediator.getCooks().size() < 7) {
						Restaurant.getInstance().setMoney(Restaurant.getInstance().getMoney()-mediator.getCooks().size() * 200);
						factory = new CookFactory();
						Cook cook = builder.setMediator(mediator).setName(mediator.getNewInitial()).setSpeed(1).setSkill(25).buildCook();
						mediator.addUser(cook);
					}else {
						System.out.println("Cooker reached maximal");
					}
					
					
				}else {
					System.out.println("Money Not Enough");
				}
				break;
			}
		} while (choice != 3);
	}
	
	
	
	public void UpgradeWaiter() {
		Scanner scanf = new Scanner(System.in);;
		int choice = -1;
		ArrayList<Waiter> waiters = mediator.getWaiters();
		do {
			choice = 0;
			
			HighScore.getInstance().printHyphens(27);
			System.out.printf("| %-4s | %-7s | %-6s |%n", "No.", "Initial", "Speed");
			HighScore.getInstance().printHyphens(27);
			int i = 0;
			for (Waiter waiter : waiters) {
				i++;
				System.out.printf("| %-4s | %-7s | %-6s |%n",i,waiter.getName(), waiter.getSpeed());
			}
			HighScore.getInstance().printHyphens(27);
			System.out.print("Input employee's number to upgrade [0 to exit] : ");
			choice = scanf.nextInt();
			if(Restaurant.getInstance().getMoney() >= 150) {
				if(choice > 0 && choice <= waiters.size()) {
					Waiter waiter = waiters.get(choice-1);
					if(waiter.getSpeed() < 5) {
						Restaurant.getInstance().setMoney(Restaurant.getInstance().getMoney() - 150);
						waiter.setSpeed(waiter.getSpeed() + 1);
					}else {
						
					}
				}else {
					System.out.println("The Waiter is Not Exist");
				}
			}else {
				System.out.println("Money Not Enough");
			}
			
			
		} while (choice != 0);
	}
	
	public void cls() {
		for(int i = 0;i<100;i++) {
			System.out.println("");
		}
	}
	
	public void UpgradeCooks() {
		Scanner scanf = new Scanner(System.in);;
		int choice = -1;
		int upgchoice = -1;
		ArrayList<Cook> cooks = mediator.getCooks();
		do {
			choice = 0;
			
			HighScore.getInstance().printHyphens(33);
			System.out.printf("| %-4s | %-7s | %-6s | %-6s |%n", "No.", "Initial", "Speed","Skill");
			HighScore.getInstance().printHyphens(33);
			int i = 0;
			for (Cook cook : cooks) {
				i++;
				System.out.printf("| %-4s | %-7s | %-6s | %-6s |%n",i,cook.getName(), cook.getSpeed(),cook.getSkill());
			}
			HighScore.getInstance().printHyphens(33);
			System.out.print("Input employee's number to upgrade [0 to Exit] : ");
			choice = scanf.nextInt();
			if(Restaurant.getInstance().getMoney() >= 150) {
				if(choice > 0 && choice <= cooks.size()) {
					upgchoice = -1;
					System.out.println("1. Skill");
					System.out.println("2. Speed");
					System.out.println("Input what do you want to Upgrade [0 to Exit]");
					Cook cook = cooks.get(choice-1);
					switch(upgchoice) {
						case 1:
							if(cook.getSkill() < 5) {
								Restaurant.getInstance().setMoney(Restaurant.getInstance().getMoney() - 150);
								cook.setSkill(cook.getSkill() + 1);
							}else {
								System.out.println("This Cooker Skill is Max");
							}
							break;
						case 2:
							if(cook.getSpeed() < 5) {
								Restaurant.getInstance().setMoney(Restaurant.getInstance().getMoney() - 150);
								cook.setSpeed(cook.getSpeed() + 1);
							}else {
								System.out.println("This Cooker Speed is Max");
							}
							break;
					}
					
					
				}else {
					System.out.println("The Cooker is Not Exist");
				}
			}else {
				System.out.println("Money Not Enough");
			}
			
			
		} while (choice != 0 && upgchoice != 0);
	}
	

	

}
