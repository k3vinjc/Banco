/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banco;

/**
 *
 * @author christianescobar
 */
public class Tarjeta {
    private String Numero;
    MySqlHanddler MSH;
    Cuenta cuenta;
    
    public Tarjeta(String Numero){
        this.Numero=Numero;
        MSH=new MySqlHanddler();
    }
    public int Coneccion(){
        return MSH.Conectar();
    }
    public String getNumero(){
        return this.Numero;
    }
    public int ExisteTarjeta(){
        return MSH.ExisteTarjeta(this);
    }
    public int SaldoSuficiente(double monto){
        return MSH.SaldoTarjeta(this, monto);
    }
}
