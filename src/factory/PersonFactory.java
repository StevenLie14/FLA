package factory;

import builder.FactoryBuilder;
import model.Person;

public abstract class PersonFactory {

	
	public abstract Person createPerson(FactoryBuilder fact);

}
