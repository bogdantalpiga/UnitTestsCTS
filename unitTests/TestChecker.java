package ase.cts.unitTests;

import ase.cts.pizza.Checker;

import ase.cts.pizza.Client;
import ase.cts.pizza.EmailChecker;

import ase.cts.pizza.NumeException;
import ase.cts.pizza.PhoneChecker;
import ase.cts.pizza.PlataCard;
import ase.cts.pizza.StrategiePlata;

import static org.junit.Assert.*;
import org.junit.*;

public class TestChecker {
    StrategiePlata s;

    @Before
    public void creareStrategie() {
        try {
            s = new PlataCard("ROXXXX12432");
        } catch (NumeException e) {
        }
    }

    @Test
    public void testEmailCheckerConditiiNormale() {

        Client client = null;
        try {
            client = new Client("Ana", null, "ana.maria@yahoo.com", s);
        } catch (NumeException e) {
            fail();
        }
        Checker chain = new EmailChecker(client);
        boolean b = chain.validate();
        assertTrue("Test email checker", b);
    }

    @Test
    public void testEmailCheckerClientNull() {
        Client client = null;
        Checker chain = new EmailChecker(client);
        assertFalse("Test client null", chain.validate());
    }

    @Test
    public void testPhoneCheckerClientNull() {
        Client client = null;
        Checker chain = new PhoneChecker(client);
        assertFalse("Test client null", chain.validate());
    }

    @Test
    public void testTelefonCheckerConditiiNormale() {

        Client client = null;
        try {
            client = new Client("Ana", "123425324", null, s);
        } catch (NumeException e) {
            fail();
        }
        Checker chain = new PhoneChecker(client);
        boolean b = chain.validate();
        assertTrue("Test email checker", b);
    }

    @Test
    public void testChainOfCheckersNormal() {
        Client client = null;
        try {
            client = new Client("Ana", "123425234", "ana.maria@yahoo.com", s);
        } catch (NumeException e) {
            fail();
        }
        EmailChecker check = new EmailChecker(client);
        PhoneChecker phoneChecker = new PhoneChecker(client);

        phoneChecker.setNext(check);

        boolean b = phoneChecker.validate();
        assertTrue("Test chain of checkers normal values", b);
    }

    @Test
    public void testChainOfCheckersWithNullValue() {
        Client client = null;
        try {
            client = new Client("Ana", null, "ana.maria@yahoo.com", s);
        } catch (NumeException e) {
            fail();
        }
        EmailChecker check = null;
        PhoneChecker phoneChecker = new PhoneChecker(client);

        phoneChecker.setNext(check);

        boolean b = phoneChecker.validate();
        assertFalse("Test chain of checkers null values", b);
    }

    @Test
    public void testChainOfCheckersWithNullClient() {
        Client client = null;

        EmailChecker check = new EmailChecker(client);
        PhoneChecker phoneChecker = new PhoneChecker(client);

        phoneChecker.setNext(check);

        boolean b = phoneChecker.validate();
        assertFalse("Test chain of checkers client null", b);
    }


    @Test
    public void testSetClientNullToEmailChecker() {
        Client client = null;

        try {
            client = new Client("Gigel", null, null, s);
        } catch (NumeException e) {
            fail();
        }
        EmailChecker checker = new EmailChecker(client);
        assertNotNull("Test initializare email checker cu client existent",
                      checker.getClient());

        try {
            checker.setClient(null);
            fail();
        } catch (NullPointerException e) {

        } catch (Exception e) {
            fail("Nu se poate seta un client null");
        }

        assertNotNull("Test setare client null", checker.getClient());
    }

    @Test
    public void testSetClientNullToPhoneChecker() {
        Client client = null;

        try {
            client = new Client("Gigel", null, null, s);
        } catch (NumeException e) {
            fail();
        }
        PhoneChecker checker = new PhoneChecker(client);
        assertNotNull("Test initializare phone checker cu client existent",
                      checker.getClient());

        try {
            checker.setClient(null);
            fail();
        } catch (NullPointerException e) {

        } catch (Exception e) {
            fail("Nu se poate seta un client null");
        }

        assertNotNull("Test setare client null", checker.getClient());
    }
}
