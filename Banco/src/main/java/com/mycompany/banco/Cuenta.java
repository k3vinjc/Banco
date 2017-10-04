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
public class Cuenta {
    private String Numero;
    MySqlHanddler MSH;
    public Cuenta(String Numero){
        this.Numero=Numero;
        MSH=new MySqlHanddler();
    }
    public int Coneccion(){
        return MSH.Conectar();
    }
    public String getNumero(){
        return this.Numero;
    }
    public int ExisteCuenta(){
        return MSH.ExisteCuenta(this);
    }
}
