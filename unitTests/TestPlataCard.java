package ase.cts.unitTests;

import ase.cts.pizza.NumeException;
import ase.cts.pizza.PlataCard;

import static org.junit.Assert.*;
import org.junit.*;

public class TestPlataCard {
    
    @Test
    public void testInitializarePlataCard(){
        PlataCard plata=null;
        try {
            plata = new PlataCard("ROBUC142535");
        } catch (NumeException e) {
            fail();
        }
        assertNotNull("Test initializare plata cu cardul", plata);
        
    }
    
    @Test
    public void testInitilizarePlataCardNull(){
        try{
            PlataCard plata=new PlataCard(null);
            fail("Iban-ul nu poate fi null");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail();       
        } 
    }
    
    @Test
    public void testInitilizarePlataCardGol(){
        try{
            PlataCard plata=new PlataCard("");
            fail("Iban-ul nu poate fi gol");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail();       
        } 
    }
    
    @Test
    public void testPlatireIbanGol(){
        try{
            PlataCard plata=new PlataCard("");
            plata.plateste();
            fail("plata nu poate fi efectuata dintr-un iban gol");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail();       
        } 
    }
    
    @Test
    public void testPlatireIbanNull(){
        try{
            PlataCard plata=new PlataCard(null);
            plata.plateste();
            fail("plata nu poate fi efectuata dintr-un iban null");
        }catch(NumeException e){
                
        }catch(Exception e){
            fail();       
        } 
    }
    
    @Test
    public void testPlatireIbanExistent(){
        try{
            PlataCard plata=new PlataCard("IBAN23423565");
            plata.plateste();
            
        }catch(NumeException e){
            fail();
        }catch(Exception e){
            fail();       
        } 
    }
}
