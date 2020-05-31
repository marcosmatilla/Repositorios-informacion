package uo.ri.cws.extended.course;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddDedicationsTests.class, ClearDedicationsTests.class,
		ConstructorValidationTests.class, DedicationConstructorTests.class })
public class AllTests {

}
