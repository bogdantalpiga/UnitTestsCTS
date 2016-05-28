package ase.cts.unitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
{TestChecker.class,    
 TestClientObserver.class,
 TestComanda.class,
 TestCrearePizzaFacade.class,
 TestNodStructuraComposite.class,
 TestPizzaBuilder.class,
 TestPizzerieSingleton.class,
 TestPlataCard.class
 }
)
public class TestSuite {
    
}
