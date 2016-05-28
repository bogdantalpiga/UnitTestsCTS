package ase.cts.unitTests;

import ase.cts.pizza.Apa;
import ase.cts.pizza.Clatita;
import ase.cts.pizza.Client;
import ase.cts.pizza.Comanda;
import ase.cts.pizza.CrearePizzaFacade;
import ase.cts.pizza.EmailChecker;
import ase.cts.pizza.INotificabil;

import ase.cts.pizza.NumeException;
import ase.cts.pizza.Papanas;
import ase.cts.pizza.PhoneChecker;

import ase.cts.pizza.Pizza;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ase.cts.pizza.Pizzerie;

import ase.cts.pizza.PlataTransfer;
import ase.cts.pizza.StrategiePlata;

import ase.cts.pizza.Suc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class TestPizzerieSingleton {

    private String nume;
    private String telefon;
    private String mail;

    public static Collection<Object[]> parse() {
        BufferedReader br = null;
        ArrayList<Object[]> rez = new ArrayList<Object[]>();
        
        try {
            br =
 new BufferedReader(new InputStreamReader(new FileInputStream("./DateTest.txt")));
            String line = "";
            line = br.readLine();
            while (line != null) {
                String[] args = line.split(",");
                rez.add(args);
                line = br.readLine();
            }
            br.close();
            return rez;
        } catch (Exception e) {
            e.printStackTrace();
            if (br != null)
                try {
                    br.close();
                } catch (IOException e1) {

                    e1.printStackTrace();
                }
        }
        return null;
    }


    @Parameters
    public static Collection<Object[]> getDatas() {
        return parse();
    }

    public TestPizzerieSingleton(String nume, String telefon, String mail) {
        super();
        this.nume = nume;
        this.telefon = telefon;
        this.mail = mail;
    }


    @After
    public void destroySingleton() {
        Pizzerie pizzerie = null;
        try {
            pizzerie = Pizzerie.getInstance();
            while (pizzerie.childSize() > 0) {
                pizzerie.removeChild(0);
            }
            while (pizzerie.getNumarClienti() > 0) {
                pizzerie.removeClient(0);
            }
        } catch (IOException e) {
        }
    }


    @Test
    public void testInitializareInstanta() {
        Pizzerie pizzerie = null;
        try {
            pizzerie = Pizzerie.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Crearea singleton a esuat");
        }
        assertNotNull("Test initializare pizzerie", pizzerie);
    }

    @Test
    public void testSameObject() {
        Pizzerie pizzerie = null;
        Pizzerie pizzerie2 = null;
        try {
            pizzerie = Pizzerie.getInstance();
            pizzerie2 = Pizzerie.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Crearea singleton a esuat");
        }
        assertEquals("Test same Object", pizzerie, pizzerie2);
    }

    @Test
    public void testAdaugareClient() {
        Pizzerie pizzerie = null;
        try {
            pizzerie = Pizzerie.getInstance();
        } catch (IOException e) {
            fail();
        }
        Client client = null;
        try {
            StrategiePlata sp = new PlataTransfer();
            client = new Client(nume, telefon, mail, sp);
        } catch (Exception e) {
            fail();
        }
        pizzerie.addClient(client);
        int expected = 1;
        assertEquals("Test adaugare client simplu", expected,
                     pizzerie.getNumarClienti());
    }

    @Test
    public void testAdaugareClientNull() {
        Pizzerie pizzerie = null;
        try {
            pizzerie = Pizzerie.getInstance();
        } catch (IOException e) {
            fail();
        }
        Client client = null;
        pizzerie.addClient(client);
        int expected = 0;
        assertEquals("Test adaugare client simplu", expected,
                     pizzerie.getNumarClienti());
    }

    @Test
    public void testPregatesteComanda() {
        Client c = null;
        Pizzerie pizzerie = null;
        Comanda comanda1 = null;

        try {
            StrategiePlata sp = new PlataTransfer();
            c = new Client("Tudor", null, null, sp);
            pizzerie = Pizzerie.getInstance();

            comanda1 = new Comanda("1234");
            comanda1.addConsumabil(new Pizza.PizzaBuilder("Quatro Stagioni",
                                                          250).addSalam("sibiu").build());
            c.addComanda(comanda1);


            pizzerie.addClient(c);
            pizzerie.pregatesteComenzi();
        } catch (NumeException e) {
            fail();
        } catch (IOException e) {
            fail();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPregatesteComenziNule() {
        Client c = null;
        Pizzerie pizzerie = null;
        Comanda comanda1 = null;
        Comanda comanda2 = null;
        try {
            StrategiePlata sp = new PlataTransfer();
            c = new Client("Tudor", null, null, sp);
            pizzerie = Pizzerie.getInstance();

            comanda1 = null;
            comanda2 = null;
            c.addComanda(comanda1);
            c.addComanda(comanda2);

            pizzerie.addClient(c);
            pizzerie.pregatesteComenzi();
        } catch (NumeException e) {
            fail();
        } catch (IOException e) {
            fail();
        } catch (Exception e) {
            fail();
        }
    }
}
