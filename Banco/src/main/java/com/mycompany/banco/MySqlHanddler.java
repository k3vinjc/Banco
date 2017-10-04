/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author christianescobar
 */
public class MySqlHanddler {
    private Connection Coneccion;
    private String CadenaConeccion;
    
    public MySqlHanddler()
    {
          
    }
    public int Conectar(){
        int retorno=0;// Todo Correcto
        try{  
            Class.forName("com.mysql.jdbc.Driver");
            CadenaConeccion="jdbc:mysql://www.banco.com:3306/banco";
            Coneccion=DriverManager.getConnection(CadenaConeccion,"Usuario","1234");
            retorno=1;
        }catch(Exception e){
            retorno=0; //error
        }
        return retorno;
    }
    //Ver que si existe la tarjeta. Si existe retorna 1, en caso que no, retorna 0.
    public int ExisteTarjeta(Tarjeta tarjeta){
        int retorno=0;
        try{
            Coneccion=DriverManager.getConnection(CadenaConeccion,"Usuario","1234");
            Statement stmt=Coneccion.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from tarjeta where  numero='"+tarjeta.getNumero()+"'"); 
            while(rs.next())
                retorno=1; 
            Coneccion.close();  
        }catch(Exception e){
            retorno=0; //error
        }
        return retorno;
    }
    //Ver que si existe la tarjeta. Si existe retorna 1, en caso que no, retorna 0.
    public int ExisteCuenta(Cuenta cuenta){
        int retorno=0;
        try{
            Coneccion=DriverManager.getConnection(CadenaConeccion,"Usuario","1234");
            Statement stmt=Coneccion.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from cuenta where numero_Cuenta='"+cuenta.getNumero()+"'"); 
            while(rs.next())
                retorno=1; 
            Coneccion.close();  
        }catch(Exception e){
            retorno=0; //error
        }
        return retorno;
    }
    //Ver que si existe la tarjeta. Si existe retorna 1, en caso que no, retorna 0.
    public int SaldoTarjeta(Tarjeta tarjeta, double monto){
        int retorno=0;
        try{
            Coneccion=DriverManager.getConnection(CadenaConeccion,"Usuario","1234");
            Statement stmt=Coneccion.createStatement();  
            ResultSet rs=stmt.executeQuery("select saldo from cuenta inner join tarjeta on tarjeta.cuenta=cuenta.dbid where tarjeta.numero='"+tarjeta.getNumero()+"'"); 
            while(rs.next())
                if(rs.getDouble(1)>=monto){
                    retorno=1;
                } 
            Coneccion.close();  
        }catch(Exception e){
            retorno=0; //error
        }
        return retorno;
    }
}
