package ase.cts.unitTests;

import ase.cts.pizza.NumeException;
import ase.cts.pizza.Pizza;

import static org.junit.Assert.*;
import org.junit.*;

public class TestPizzaBuilder {
    
    @Test
    public void testValoriNormale(){
        Pizza pizza=null;
        try {
            pizza = new Pizza.PizzaBuilder("Pizza normala",200).addRosii("Cherry").addSalam("Sasesc").addCascaval("Cas").build();
        } catch (NumeException e) {
            fail("Initializarea pizzei a esuat");
        }
        assertNotNull("Test valori normale", pizza);
    }
    
    @Test
    public void testGramajMaxInt(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder("Pizza normala",Integer.MAX_VALUE).build();
            fail("Pizza nu poate fi initializata cu acest gramaj");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }
        
    }
    
    @Test
    public void testGramajMinInt(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder("Pizza normala",Integer.MIN_VALUE).build();
            fail("Pizza nu poate fi initializata cu acest gramaj");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }
        
    }
    
    @Test
    public void testGramajNegativNormal(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder("Pizza normala",-1).build();
            fail("Pizza nu poate fi initializata cu gramaj negativ");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }
        
    }
    
    
    @Test
    public void testNumeNull(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder(null,150).build();
            fail("Pizza nu poate fi initializata fara nume");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }
        
    }
    
    @Test
    public void testIngredientRosieNull(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder("Pizza normala",300).addRosii(null).build();
            fail("Pizza nu poate fi initializata cu ingrediente nule");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }
        
    }
    @Test
    public void testIngredientCascavalNull(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder("Pizza normala",150).addCascaval(null).build();
            fail("Pizza nu poate fi initializata cu ingrediente nule");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }
        
    }
    @Test
    public void testIngredientSalamNull(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder("Pizza normala",150).addSalam(null).build();
            fail("Pizza nu poate fi initializata cu ingrediente nule");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }
        
    }
    
    @Test
    public void testGramajValoare0(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder("Pizza normala",0).build();
            fail("Pizza nu poate fi initializata cu gramajul fiind 0");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }
        
    }
    
    @Test
    public void testNumeGol(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder("",275).build();
            fail("Pizza nu poate fi initializata fara nume");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }
        
    }
    
    
    @Test
    public void testIngredientRosieGol(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder("Pizza normala",150).addRosii("").build();
            fail("Pizza nu poate fi initializata cu ingrediente nule");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }
        
    }
    @Test
    public void testIngredientCascavalGol(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder("Pizza normala",150).addCascaval("").build();
            fail("Pizza nu poate fi initializata cu ingrediente nule");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }
        
    }
    @Test
    public void testIngredientSalamGol(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder("Pizza normala",300).addSalam("").build();
            fail("Pizza nu poate fi initializata cu ingrediente nule");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }
        
    }
    
    @Test
    public void testIngredienteGoaleNuleSiAcceptate(){
        try{
            Pizza pizza=new Pizza.PizzaBuilder("Pizza normala",300).addRosii("Cherry").addSalam("").addCascaval(null).build();
            fail("Pizza nu poate fi initializata cu ingrediente nule");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail("Exceptie neasteptata");
        }     
    }
    
}
