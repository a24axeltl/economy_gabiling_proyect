/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mycompany.psphilosbroker.DataSaveOperacionUtilies;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import model.Agente;
import model.Operacion;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class EstadoMercado {
    private double precioActual;
    private HashMap<Integer,Agente> mapaAgentes = new HashMap<>();
    private List<Operacion> operacionesCompra = new ArrayList<>();
    private List<Operacion> operacionesVenta = new ArrayList<>();

    public EstadoMercado(double precioActual) {
        this.precioActual = precioActual;
    }
    
    public synchronized void anhadirOperacion(Operacion op){ //syncronization a la hora de añadir las operaciones a las listas
        switch (op.getTipo()) {
            case "compra":
                operacionesCompra.add(op);
                operacionesCompra.sort(Comparator.comparing(Operacion::getLimite).reversed()); //Ordenamos la lista de Compra de forma descendente
                break;
            case "venta":
                operacionesVenta.add(op);
                operacionesVenta.sort(Comparator.comparing(Operacion::getLimite)); //Ordenamos la lista de Venta de forma ascendente
                break;
            default:
                System.err.println("Tipo invalido o no identificado");
                break;
        }
    }
    
    public synchronized void anhadirAgente(Agente ag){
        mapaAgentes.put(ag.getID(), ag);
    }
    
    public synchronized double getPrecioActual(){
        return this.precioActual;
    }
    
    public synchronized void setPrecioActual(double precio){
        this.precioActual = precio;
    }
    
    public synchronized int getNumOperacionesCompra(){
        return this.operacionesCompra.size();
    }
    
    public synchronized int getNumOperacionesVenta(){
        return this.operacionesVenta.size();
    }
    
    public synchronized Collection<Agente> getAgentes(){
        return mapaAgentes.values();
    }
    
    public synchronized Operacion getOperacionCompraCara(){
        if(operacionesCompra.isEmpty()){
            return null;
        } else {
            return operacionesCompra.get(0);
        }
    }
    
    public synchronized Operacion getOperacionVentaBarata(){
        if(operacionesVenta.isEmpty()){
            return null;
        } else {
            return operacionesVenta.get(0);
        }
    }
    
    public synchronized Agente getAgente(int id){
        return mapaAgentes.get(id);
    }
    
    public synchronized void eliminarOperacionCompra(Operacion op){
        operacionesCompra.remove(op);
        DataSaveOperacionUtilies.eliminarOperacion(op.getRefAgenteID(), "compra");
    }
    
    public synchronized void eliminarOperacionVenta(Operacion op){
        operacionesVenta.remove(op);
        DataSaveOperacionUtilies.eliminarOperacion(op.getRefAgenteID(), "venta");
    }
}
