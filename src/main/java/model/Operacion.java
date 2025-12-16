/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.Agente;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class Operacion {
    private String tipo;
    private double limite;
    private double cantidad;
    private transient Agente refAgente;

    public Operacion(String tipo, double limite, double cantidad, Agente refAgente) {
        setTipo(tipo);
        this.limite = limite;
        this.cantidad = cantidad;
        this.refAgente = refAgente;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        if(tipo.equalsIgnoreCase("compra") || tipo.equalsIgnoreCase("venta")){
            this.tipo = tipo;
        }
    }

    /**
     * @return the limite
     */
    public double getLimite() {
        return limite;
    }

    /**
     * @param limite the limite to set
     */
    public void setLimite(double limite) {
        this.limite = limite;
    }

    /**
     * @return the cantidad
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Operacion{" + "tipo=" + tipo + ", limite=" + limite + ", cantidad=" + cantidad + '}';
    }
}
