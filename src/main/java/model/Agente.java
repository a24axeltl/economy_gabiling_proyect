/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.EstadoMercado;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class Agente {
    private int ID;
    private String nome;
    private double saldo;
    private Operacion operacionCompra;
    private Operacion operacionVenta;

    public Agente(int ID, String nome, double saldo) {
        this.ID = ID;
        this.nome = nome;
        this.saldo = saldo;
    }
    
    
    
    public boolean nuevaOperacionCompra(String tipo, double limite, double cantidad, EstadoMercado broker){
        switch (tipo) {
            case "compra":
                if(operacionCompra == null){
                    operacionCompra = new Operacion(tipo, limite, cantidad, this);
                    broker.anhadirOperacion(operacionCompra);
                } else {
                    System.out.println("Ya existe una operacion de compra para el agente " + getNome());
                    return false;
                }
                break;
            case "venta":
                if(operacionVenta == null){
                    operacionVenta = new Operacion(tipo, limite, cantidad, this);
                    broker.anhadirOperacion(operacionVenta);
                } else {
                    System.out.println("Ya existe una operacion de venta para el agente " + getNome());
                    return false;
                }
                break;
            default: return false;
        }
        
        return true;
    }
    
    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the operacionCompra
     */
    public Operacion getOperacionCompra() {
        return operacionCompra;
    }

    /**
     * @param operacionCompra the operacionCompra to set
     */
    public void setOperacionCompra(Operacion operacionCompra) {
        this.operacionCompra = operacionCompra;
    }

    /**
     * @return the operacionVenta
     */
    public Operacion getOperacionVenta() {
        return operacionVenta;
    }

    /**
     * @param operacionVenta the operacionVenta to set
     */
    public void setOperacionVenta(Operacion operacionVenta) {
        this.operacionVenta = operacionVenta;
    }

    @Override
    public String toString() {
        return "Agente{" + 
                "ID=" + ID + 
                ", nome='" + nome + '\'' + 
                ", saldo=" + saldo + 
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.ID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agente other = (Agente) obj;
        return this.ID == other.ID;
    }
}
