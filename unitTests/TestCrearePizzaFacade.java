package ase.cts.unitTests;

import ase.cts.pizza.CrearePizzaFacade;

import ase.cts.pizza.Pizza;

import static org.junit.Assert.*;
import org.junit.*;

public class TestCrearePizzaFacade {
    
    
    @Test
    public void testCreareFacade(){
        CrearePizzaFacade facade=new CrearePizzaFacade();
        assertNotNull("Test creare pizza facade", facade);
    } 
    
    @Test
    public void testCrearePizza(){
        CrearePizzaFacade facade=new CrearePizzaFacade();
        Pizza pizza= facade.crearePizza();
        String expName="Pizza standard", expCascaval="Mozzarella", expRosii="Cherry",expSalam="Sasesc";
        int expGramaj=300;
        assertNotNull("Test creare pizza standard prin facade", pizza);
        assertEquals("Test create pizza standard", expName, pizza.getNume());
        assertEquals("Test create pizza standard", expGramaj, pizza.getGramaj());
        assertEquals("Test create pizza standard", expCascaval, pizza.getCascaval());
        assertEquals("Test create pizza standard", expRosii, pizza.getRosii());
        assertEquals("Test create pizza standard", expSalam, pizza.getSalam());
    }
    
    @Test
    public void destPizzeDiferite(){
        CrearePizzaFacade facade=new CrearePizzaFacade();
        Pizza pizzaStandard1=facade.crearePizza();
        Pizza pizzaStandard2=facade.crearePizza();
        
        assertNotSame("Test pizze diferite", pizzaStandard1, pizzaStandard2);
    }
}
