/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Agente;
import model.Operacion;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class TareaBolsa implements Runnable{
    private EstadoMercado broker;

    public TareaBolsa(EstadoMercado broker) {
        this.broker = broker;
    }
    
    @Override
    public void run() {
        //Ejecucion del hilo usando el broker
        while(true){
            synchronized (broker) {
                Operacion opCompra = broker.getOperacionCompraBarata();
                Operacion opVenta = broker.getOperacionVentaCara();

                if (opCompra.getLimite() >= opVenta.getLimite()) {
                    Agente agCompra = opCompra.getRefAgente();
                    Agente agVenta = opVenta.getRefAgente();
                    double precioVenta = opVenta.getLimite();
                    int cantidadTransaccion = Math.min(opCompra.getCantidad(), opVenta.getCantidad()); //Minimo de la cantidad de operacion compra y venta.
                    double precioOperacion = precioVenta * cantidadTransaccion;

                    agCompra.setSaldo(agCompra.getSaldo() - precioOperacion);
                    agVenta.setSaldo(agVenta.getSaldo() + precioOperacion);

                    if (opCompra.getCantidad() == 0) {
                        broker.eliminarOperacionCompra(opCompra);
                    }
                    if (opVenta.getCantidad() == 0) {
                        broker.eliminarOperacionVenta(opVenta);
                    }
                    broker.setPrecioActual(precioVenta);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
}
