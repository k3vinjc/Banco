/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banco;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author christianescobar
 */
public class MySqlHanddlerTest {
    
    public MySqlHanddlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of Conectar method, of class MySqlHanddler.
     */
    @Test
    public void testConectar() {
        System.out.println("Test MySqlHanddler Conectar.");
        MySqlHanddler instance = new MySqlHanddler();
        int expResult = 1;
        int result = instance.Conectar();
        assertEquals(expResult, result);
    }

    /**
     * Test of ExisteTarjeta method, of class MySqlHanddler.
     */
    @Test
    public void testNoExisteTarjeta() {
        System.out.println("Test MySqlHanddler Tarjeta No existe.");
        MySqlHanddler instance = new MySqlHanddler();
        instance.Conectar();
        double ExpResulst = 0;
        double result = instance.ExisteTarjeta(new Tarjeta("20"));
        assertEquals(ExpResulst,result);
    }

    /**
     * Test of ExisteCuenta method, of class MySqlHanddler.
     */
    @Test
    public void testNoExisteCuenta() {
        System.out.println("Test MySqlHanddler Tarjeta No existe.");
        MySqlHanddler instance = new MySqlHanddler();
        instance.Conectar();
        double ExpResulst = 0;
        double result = instance.ExisteCuenta(new Cuenta("20"));
        assertEquals(ExpResulst,result);
    }

    /**
     * Test of SaldoTarjeta method, of class MySqlHanddler.
     */
    @Test
    public void testSaldoTarjeta() {
        
    }
    
}
