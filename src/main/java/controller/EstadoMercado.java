/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Operacion;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class EstadoMercado {
    private double precioActual;
    private List<Operacion> operacionesCompra = new ArrayList<>();
    private List<Operacion> operacionesVenta = new ArrayList<>();

    public EstadoMercado(double precioActual) {
        this.precioActual = precioActual;
    }
    
    public void anhadirOperacion(Operacion op){
        switch (op.getTipo()) {
            case "compra":
                operacionesCompra.add(op);
                break;
            case "venta":
                operacionesVenta.add(op);
                break;
            default:
                System.err.println("Tipo invalido o no identificado");
                break;
        }
    }
}
