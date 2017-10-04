/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banco;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.text.DecimalFormat;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author chris
 */
@WebService(serviceName = "Banco")
public class Banco {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "transferencia_Cuenta")
    public String transferencia_Cuenta(@WebParam(name = "no_Tarjeta") String no_Tarjeta, @WebParam(name = "cuenta_Destino") String cuenta_Destino,@WebParam(name = "monto") double monto) throws JsonProcessingException {
        DecimalFormat df = new DecimalFormat("#.00"); 
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();
        if(!no_Tarjeta.equals("") && !cuenta_Destino.equals("") && monto>0){
            Tarjeta tarjeta=new Tarjeta(no_Tarjeta);
            Cuenta cuenta=new Cuenta(cuenta_Destino);
            int coneccionTarjeta=tarjeta.Coneccion();
            int coneccionCuenta=cuenta.Coneccion();
            if(coneccionTarjeta==0 && coneccionCuenta==0){
                ((ObjectNode) rootNode).put("status", "1");
                ((ObjectNode) rootNode).put("descripción", "No se pudo conectar a la base de datos.");
            }else{
                int ExisteTarjeta=tarjeta.ExisteTarjeta();
                int ExisteCuenta=cuenta.ExisteCuenta();
                if(ExisteTarjeta==0){
                    ((ObjectNode) rootNode).put("status", "1");
                    ((ObjectNode) rootNode).put("descripción", "No existe el numero de tarjeta indicado.");
                }else if(ExisteCuenta==0){
                    ((ObjectNode) rootNode).put("status", "1");
                    ((ObjectNode) rootNode).put("descripción", "No existe el numero de cuenta indicado.");
                }else{
                    ((ObjectNode) rootNode).put("status", "0");
                    ((ObjectNode) rootNode).put("costo_Aduana", "");
                    ((ObjectNode) rootNode).put("descripción", "Exitoso");
                }
            }
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        }else{
            ((ObjectNode) rootNode).put("status", "1");
            ((ObjectNode) rootNode).put("descripción", "Los parametros son incorrectos");
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        }
    }
}
