package ase.cts.unitTests;

import ase.cts.pizza.Client;

import ase.cts.pizza.Comanda;
import ase.cts.pizza.CrearePizzaFacade;
import ase.cts.pizza.NumeException;
import ase.cts.pizza.Pizza;
import ase.cts.pizza.PlataCard;
import ase.cts.pizza.PlataTransfer;
import ase.cts.pizza.StrategiePlata;

import static org.junit.Assert.*;
import org.junit.*;

public class TestClientObserver {
   
    @Test
    public void testInitializareClientValoriNormale(){
        StrategiePlata card=null;
        try {
            card = new PlataCard("RO234928");
        } catch (NumeException e) {
        }
        Client client=null;
        try {
            client = new Client("Bogdan","0283172938","bogdan@yahoo.com",card);
        } catch (NumeException e) {
            fail();
        }
        assertNotNull("Test initializare client valori normale", client);
    }
    
    @Test
    public void testInitializareClientNumeNull(){
        StrategiePlata card=null;
        try {
            card = new PlataCard("RO234928");
        } catch (NumeException e) {
        }
        try{
            Client client=new Client(null,"0283172938","bogdan@yahoo.com",card);
            fail("Numele nu poate fi null");
        }catch(NumeException exp){
            
        }catch(Exception e){
            fail();    
        }
    }
    
    @Test
    public void testInitializareClientNumeGol(){
        StrategiePlata card=null;
        try {
            card = new PlataCard("RO234928");
        } catch (NumeException e) {
        }
        try{
            Client client=new Client(null,"0283172938","bogdan@yahoo.com",card);
            fail("Numele nu poate fi gol");
        }catch(NumeException exp){
            
        }catch(Exception e){
            fail();    
        }
    }
    
    @Test
    public void testInitializareClientStrategieNull(){
        StrategiePlata card=null;
        try{
            Client client=new Client("Bogdan","0283172938","bogdan@yahoo.com",card);
            fail("Strategia nu poate fi nula");
        }catch(NumeException exp){
            fail();
        }catch(NullPointerException exp){
                
        }catch(Exception e){
            fail();       
        }
    }
    
    @Test
    public void testAddComanda(){
        Comanda comm=null;
        try {
            comm = new Comanda("123");
            StrategiePlata card=new PlataCard("RO234928");
            Client client=new Client("Bogdan","0283172938","bogdan@yahoo.com",card);
            client.addComanda(comm);
            int expectedComands=1;
            assertEquals("Test adaugare comanda client", expectedComands, client.getComenzi().size());
        } catch (NumeException e) {
            fail();
        }
    }
    
    @Test
    public void testAddComandaNula(){
        Comanda comm=null;
        try {
            
            StrategiePlata card=new PlataCard("RO234928");
            Client client=new Client("Bogdan","0283172938","bogdan@yahoo.com",card);
            client.addComanda(comm);
            int expectedComands=0;
            assertEquals("Test adaugare comanda client", expectedComands, client.getComenzi().size());
        } catch (NumeException e) {
            fail();
        }
    }
    
    @Test
    public void testRemoveComandaNula(){
        try{
            StrategiePlata transfer=new PlataTransfer();
            Client client=new Client("Bogdan","0283172938","bogdan@yahoo.com",transfer);
            Comanda comm=null;
            client.addComanda(comm);
            client.removeComanda(comm);
            int expectedComenzi=0;
            assertEquals("Test remove comanda nula", expectedComenzi, client.getComenzi().size());
        }catch(Exception e){
            fail();    
        }    
    }
    
    @Test
    public void testRemoveComandaDiferita(){
        try{
            StrategiePlata transfer=new PlataTransfer();
            Client client=new Client("Bogdan","0283172938","bogdan@yahoo.com",transfer);
            Comanda comm=null;
            Comanda comm2=new Comanda("123");
            client.addComanda(comm2);
            client.removeComanda(comm);
            int expectedComenzi=1;
            assertEquals("Test adaugare comanda si stergere comanda nula", expectedComenzi, client.getComenzi().size());
        }catch(Exception e){
            fail();    
        }    
    }
    
    @Test
    public void testAddComandaNulaRemoveOtherCommand(){
        try{
            StrategiePlata transfer=new PlataTransfer();
            Client client=new Client("Bogdan","0283172938","bogdan@yahoo.com",transfer);
            Comanda comm=null;
            Comanda comm2=new Comanda("123");
            client.addComanda(comm2);
            client.removeComanda(comm);
            int expectedComenzi=1;
            assertEquals("Test adaugare comanda nula si stergere comanda inexistenta", expectedComenzi, client.getComenzi().size());
        }catch(Exception e){
            fail();    
        }    
    }
    
    @Test
    public void testNotificareClientFaraComenzi(){
        try{
            StrategiePlata transfer=new PlataTransfer();
            Client client=new Client("Bogdan","0283172938","bogdan@yahoo.com",transfer);
            client.notifica(null);
            fail();
        }catch(NullPointerException exp){
            
        
        }catch(Exception e){
            fail();    
        }    
    }
    
    @Test
    public void testNotificareClientCuComenzi(){
        try{
            StrategiePlata transfer=new PlataTransfer();
            Client client=new Client("Bogdan","0283172938","bogdan@yahoo.com",transfer);
            Comanda comm2=new Comanda("123");
            client.notifica(comm2.getId());
        }catch(Exception e){
            fail();    
        }    
    }
    
}
