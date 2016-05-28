package ase.cts.unitTests;

import ase.cts.pizza.Apa;
import ase.cts.pizza.Bautura;
import ase.cts.pizza.Client;
import ase.cts.pizza.Comanda;
import ase.cts.pizza.NumeException;
import ase.cts.pizza.Papanas;
import ase.cts.pizza.Pizzerie;

import ase.cts.pizza.Suc;

import java.io.IOException;

import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.*;
public class TestComanda {
    
    
    @Test
    public void testComanda(){
        Comanda comanda=null;
        try {
            comanda = new Comanda("1423");
        } catch (NumeException e) {
            fail();
        }
        assertNotNull("Test comanda", comanda);
        comanda.addConsumabil(new Apa("Borsec"));
        comanda.addConsumabil(new Papanas("A la mama"));
        
        int expectedItems=2;
        assertEquals("Test comanda equals", expectedItems, comanda.getConsumabile().size());
        
    }
    
    @Test
    public void testComandaIdNull(){
        try{
            Comanda comanda=new Comanda(null);
            fail();
        }catch(NumeException exp){
                
        }catch(Exception exp){
            exp.printStackTrace();
            fail();
        }
        
    }
    
    @Test
    public void testComandaIdGol(){
        try{
            Comanda comanda=new Comanda("");
            fail();
        }catch(NumeException exp){
                
        }catch(Exception exp){
            exp.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testAdaugareConsumabilNull(){
        try{
            Comanda comanda=new Comanda("1324");
            comanda.addConsumabil(null);
            comanda.addConsumabil(new Apa("Dorna"));
            int expectedItems=1;
            assertEquals("Test adaugare consumabil null", expectedItems, comanda.getConsumabile().size());
        }catch(NumeException e){
            fail();    
        }    
    }
    
    @Test
    public void testPregatireComandaConsumabilNull(){
        try{
            Comanda comanda=new Comanda("1324");
            comanda.addConsumabil(null);
            comanda.addConsumabil(new Apa("Dorna"));
            comanda.pregatesteComanda();
        }catch(NumeException e){
            fail();    
        }catch(NullPointerException exp){
            fail();
        }  
    }
    
    @Test
    public void testGetConsumabile(){
        try{
            Comanda comanda=new Comanda("1234");
            ArrayList consumabile=comanda.getConsumabile();
            int expected=0;
            assertEquals("Test comanda get consumabile", expected, consumabile.size());
            comanda.addConsumabil(new Suc("Fanta"));
            expected=1;    
            assertEquals("Test comanda get consumabile", expected, consumabile.size());
        }catch(Exception e){
            fail();
        }    
    }
    
}
