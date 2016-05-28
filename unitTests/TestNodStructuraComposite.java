package ase.cts.unitTests;

import ase.cts.pizza.Apa;
import ase.cts.pizza.Bautura;
import ase.cts.pizza.Mancare;
import ase.cts.pizza.NumeException;
import ase.cts.pizza.Pizza;
import ase.cts.pizza.Pizzerie;

import ase.cts.pizza.Suc;

import java.io.IOException;

import static org.junit.Assert.*;
import org.junit.*;

public class TestNodStructuraComposite {
    private Pizzerie pizzerie;
    
    @Before
    public void init(){
        try {
            pizzerie = Pizzerie.getInstance();
        } catch (IOException e) {
            fail("Initializarea pizzeriei a esuat");
        }
        Bautura meniuBautura=new Bautura("Bauturi carbogazoase");
        pizzerie.addChild(meniuBautura);
        Mancare meniuMancare=new Mancare("Pizze");
        pizzerie.addChild(meniuMancare);
        try {
            meniuMancare.addChild(new Pizza.PizzaBuilder("margherita",150).build());
        } catch (NumeException e) {
            fail("Adaugarea unei pizza in meniu a esuat!");
        }
        try {
            meniuMancare.addChild(new Pizza.PizzaBuilder("capriciosa",200).build());
        } catch (NumeException e) {
            fail("Adaugarea unei pizza in meniu a esuat!");
        }
        meniuBautura.addChild(new Apa("Borsec"));
        meniuBautura.addChild(new Suc("Fanta"));
    }
    
    @After
    public void destroySingleton(){
        while(pizzerie.childSize()!=0){
            pizzerie.removeChild(0);    
        } 
    }
    
    @Test
    public void testCazNormal(){
        int nrMeniuri=pizzerie.childSize();
        assertEquals("Caz normal", 2, nrMeniuri);
    }
    
    @Test
    public void testPizze(){
        Mancare meniuPizze = (Mancare)pizzerie.getChild(1); 
        assertEquals("Testare meniu pizze", "Pizze", meniuPizze.getCategorie());
        assertEquals("Testare meniu pizze", 2,meniuPizze.childSize());
        
        try{
            meniuPizze.getNume();
            fail("Meniul de pizza nu are un nume");
        }catch(UnsupportedOperationException exp){
            
        }
    }
    
    @Test
    public void testBauturi(){
        Bautura carbogazoase = (Bautura)pizzerie.getChild(0);    
        assertEquals("Testare meniu pizze", "Bauturi carbogazoase", carbogazoase.getCategorie());
        assertEquals("Testare meniu pizze", 2,carbogazoase.childSize());
        
        try{
            carbogazoase.getNume();
            fail("Meniul de pizza nu are un nume");
        }catch(UnsupportedOperationException exp){
            
        }
    }
    
    @Test
    public void testPizzeDinMeniu(){
        Pizza margherita = (Pizza)pizzerie.getChild(1).getChild(0);    
        assertNotNull("Testare pizza margherita e null", margherita);
        assertEquals("Test denumire pizza din meniu", "margherita", margherita.getNume());
        try{
            margherita.getCategorie();    
            fail("Pizza specifica nu este o categorie");
        }catch(UnsupportedOperationException e){
            
        }
    }
    
    
    @Test
    public void testAddChild(){
        
        Bautura meniuBauturi=(Bautura)pizzerie.getChild(0);
        int expectedBauturi=2;
        assertEquals("Test add child ", expectedBauturi, meniuBauturi.childSize());
        meniuBauturi.addChild(new Suc("Fanta"));
        expectedBauturi=expectedBauturi+1;
        assertEquals("Test add child ", expectedBauturi, meniuBauturi.childSize());
    }
    
    @Test
    public void testAddChildNull(){
        
        Bautura meniuBauturi=(Bautura)pizzerie.getChild(0);
        int expectedBauturi=2;
        assertEquals("Test add child ", expectedBauturi, meniuBauturi.childSize());
        try{
        meniuBauturi.addChild(null);
            fail("Nu se poate adauga un aliment null");
        }catch(NullPointerException exp){
                
            }catch(Exception e){
                fail("Nu se poate adauga un aliment null");
            }
        assertEquals("Test add child ", expectedBauturi, meniuBauturi.childSize());
    }
    
    @Test
    public void testRemoveBautura(){
        Bautura carbogazoase=(Bautura)pizzerie.getChild(0);
        carbogazoase.removeChild(0);
        assertEquals("Test remove bautura", 1, carbogazoase.childSize());
        
        Suc suc = (Suc)carbogazoase.getChild(0);
        assertNotNull("Test remove", suc);
        assertEquals("Test suc ramas in meniu", "Fanta", suc.getNume());
        
    }
    
    @Test
    public void testRemoveBauturaPozitieNegativa(){
        Bautura carbogazoase=(Bautura)pizzerie.getChild(0);
        carbogazoase.removeChild(-1);
        int expected=2;
        assertEquals("Test remove bautura", expected, carbogazoase.childSize());
    }
    
    @Test
    public void testRemoveBauturaPozitieMaxima(){
        Bautura carbogazoase=(Bautura)pizzerie.getChild(0);
        carbogazoase.removeChild(Integer.MAX_VALUE);
        int expected=2;
        assertEquals("Test remove bautura", expected, carbogazoase.childSize());
    }
    
    @Test
    public void testRemoveAllCategories(){
        while(pizzerie.childSize()!=0){
            pizzerie.removeChild(0);    
        }  
        assertEquals("Test remove all categories", 0, pizzerie.childSize());
    }
}
