package factory;

import builder.FactoryBuilder;
import model.Person;

public class CustomerFactory extends PersonFactory{

	@Override
	public Person createPerson(FactoryBuilder fact) {
		// TODO Auto-generated method stub
		return fact.buildCustomer();
	}


}
