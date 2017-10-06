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
import java.text.DecimalFormat;

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
    public int SaldoSuficienteTarjeta(Tarjeta tarjeta, double monto){
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
    public double SaldoTarjeta(Tarjeta tarjeta){
        double retorno=0;
        try{
            Coneccion=DriverManager.getConnection(CadenaConeccion,"Usuario","1234");
            Statement stmt=Coneccion.createStatement();  
            ResultSet rs=stmt.executeQuery("select saldo from cuenta inner join tarjeta on tarjeta.cuenta=cuenta.dbid where tarjeta.numero='"+tarjeta.getNumero()+"'"); 
            while(rs.next())
                retorno=rs.getDouble(1);
            Coneccion.close();  
        }catch(Exception e){
            retorno=0; //error
        }
        return retorno;
    }
    public double SaldoCuenta(Cuenta cuenta){
        double retorno=0;
        try{
            Coneccion=DriverManager.getConnection(CadenaConeccion,"Usuario","1234");
            Statement stmt=Coneccion.createStatement();  
            ResultSet rs=stmt.executeQuery("select saldo from cuenta where cuenta.numero_Cuenta='"+cuenta.getNumero()+"'"); 
            while(rs.next())
                retorno=rs.getDouble(1);
            Coneccion.close();  
        }catch(Exception e){
            retorno=0; //error
        }
        return retorno;
    }
    public int CambiarSaldoTarjeta(Tarjeta tarjeta, double monto){
        int retorno=1;
        try{
            DecimalFormat df = new DecimalFormat("#.00"); 
            Coneccion=DriverManager.getConnection(CadenaConeccion,"Usuario","1234");
            Statement stmt=Coneccion.createStatement();  
            stmt.executeUpdate("update cuenta inner join tarjeta on tarjeta.cuenta=cuenta.dbid set saldo="+df.format(monto)+" where tarjeta.numero='"+tarjeta.getNumero()+"'"); 
            
            Coneccion.close();  
        }catch(Exception e){
            retorno=0; //error
        }
        return retorno;
    }
    public int CambiarSaldoCuenta(Cuenta cuenta, double monto){
        int retorno=1;
        try{
            DecimalFormat df = new DecimalFormat("#.00"); 
            Coneccion=DriverManager.getConnection(CadenaConeccion,"Usuario","1234");
            Statement stmt=Coneccion.createStatement();  
            stmt.executeUpdate("update cuenta set saldo="+df.format(monto)+" where cuenta.numero_Cuenta='"+cuenta.getNumero()+"'"); 
            Coneccion.close();  
        }catch(Exception e){
            retorno=0; //error
        }
        return retorno;
    }
    
    public int CodigoTransferencia(){
        int retorno=0;
        try{
            Coneccion=DriverManager.getConnection(CadenaConeccion,"Usuario","1234");
            Statement stmt=Coneccion.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'banco' AND   TABLE_NAME   = 'transferencia_Cuenta'"); 
            while(rs.next())
                retorno=rs.getInt(1);
            Coneccion.close();  
        }catch(Exception e){
            retorno=0; //error
        }
        return retorno;
    }
    
    public int InsertarTransferencia(Tarjeta tarjeta, Cuenta cuenta, double monto){
        int retorno=1;
        try{
            DecimalFormat df = new DecimalFormat("#.00"); 
            Coneccion=DriverManager.getConnection(CadenaConeccion,"Usuario","1234");
            Statement stmt=Coneccion.createStatement();  
            stmt.executeUpdate("insert into transferencia_Cuenta(no_Tarjeta, cuenta_destino, monto, fecha_transaccion) values ((select dbid from tarjeta where numero='"+tarjeta.getNumero()+"'), (select dbid from cuenta where numero_Cuenta='"+cuenta.getNumero()+"'), "+df.format(monto)+", CURDATE());"); 
            Coneccion.close();  
        }catch(Exception e){
            retorno=0; //error
        }
        return retorno;
    }
}
