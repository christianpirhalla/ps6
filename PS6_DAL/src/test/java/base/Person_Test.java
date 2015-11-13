package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {

	private static PersonDomainModel per1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
		per1 = new PersonDomainModel();
		per1.setFirstName("Christian");
		per1.setLastName("Pirhalla");
		per1.setBirthday(null);
		per1.setCity("Wilmington");
		per1.setPostalCode(19805);
		per1.setStreet("513 Birmingham Ave");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;	
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("This person should not be here",per);		
	}
	
	@Test
	public void AddPersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("This person should not be here",per);		
		PersonDAL.addPerson(per1);	
		
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("This person should have been added",per);
	}
	
	@Test
	public void UpdatePersonTest()
	{		
		PersonDomainModel per;
		final String C_LASTNAME = "Doe";
		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("This person should not be here",per);		
		PersonDAL.addPerson(per1);	
		
		per1.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(per1);
		
		per = PersonDAL.getPerson(per1.getPersonID());

		assertTrue("No name change",per1.getLastName() == C_LASTNAME);
	}

	@Test
	public void DeletePersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("This person should not be here",per);	
		
		PersonDAL.addPerson(per1);			
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("This person should have been added",per);
		
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("This person should not be here",per);	
		
	}
	
}
