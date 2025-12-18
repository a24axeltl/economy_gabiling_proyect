/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mycompany.psphilosbroker.DataSaveAgenteUtilies;
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
    
    private void ejecucionBolsa(){
        synchronized (broker) {
            Operacion opCompra = broker.getOperacionCompraCara();
            Operacion opVenta = broker.getOperacionVentaBarata();
            
            if(opCompra == null || opVenta == null){
                return;
            }
            
            if (opCompra.getLimite() >= opVenta.getLimite()) {
                Agente agCompra = opCompra.getRefAgente();
                Agente agVenta = opVenta.getRefAgente();
                
                double precioVenta = opVenta.getLimite();
                int cantidadTransaccion = Math.min(opCompra.getCantidad(), opVenta.getCantidad()); //Minimo de la cantidad de operacion compra y venta.
                double precioOperacion = precioVenta * cantidadTransaccion;
                
                opCompra.setCantidad(opCompra.getCantidad() - cantidadTransaccion);
                opVenta.setCantidad(opVenta.getCantidad() - cantidadTransaccion);
                
                agCompra.setSaldo(agCompra.getSaldo() - precioOperacion);
                agVenta.setSaldo(agVenta.getSaldo() + precioOperacion);
                
                DataSaveAgenteUtilies.guardarAgente(agCompra);
                DataSaveAgenteUtilies.guardarAgente(agVenta);
                
                if (opCompra.getCantidad() == 0) {
                    broker.eliminarOperacionCompra(opCompra);
                    agCompra.setOperacionCompra(null);
                }
                if (opVenta.getCantidad() == 0) {
                    broker.eliminarOperacionVenta(opVenta);
                    agVenta.setOperacionVenta(null);
                }
                broker.setPrecioActual(precioVenta);
                System.out.println("Precio Actual = " + broker.getPrecioActual());
            }
        }
    }
    
    @Override
    public void run() {
        //Ejecucion del hilo usando el broker
        while(true){
            try {
                if(broker.getNumOperacionesCompra() > 0 && broker.getNumOperacionesVenta() > 0){
                    ejecucionBolsa();
                    Thread.sleep(100);
                } else {
                    System.out.println("No hay Operaciones, durmiendo...");
                    Thread.sleep(300);
                }
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }  
}