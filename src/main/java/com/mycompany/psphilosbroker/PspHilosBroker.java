/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.psphilosbroker;

import controller.EstadoMercado;
import controller.FrontController;
import controller.TareaBolsa;
import javax.swing.SwingUtilities;
import model.Agente;
import model.Operacion;
import view.MainJFrame;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class PspHilosBroker {

    public static void main(String[] args) {
        EstadoMercado broker = new EstadoMercado(1000);
        
        cargarAgentes(broker);
        cargarOperaciones(broker);
        
        Thread tareaBolsa = new Thread(new TareaBolsa(broker));
        tareaBolsa.start();
        
        MainJFrame frame = new MainJFrame();
        FrontController fc = new FrontController(frame,broker);
        frame.setVisible(true);
        SwingUtilities.invokeLater(() -> frame.loadSaveAgents());
    }
    
    private static void cargarAgentes(EstadoMercado broker){
        for(Agente ag : DataSaveAgenteUtilies.cargarAgentes()){
            broker.anhadirAgente(ag);
        }
    }
    
    private static void cargarOperaciones(EstadoMercado broker){
        for(Operacion op : DataSaveOperacionUtilies.cargarOperaciones()){
            broker.anhadirOperacion(op);

            //Reconstruir la referencia del agente
            Agente ag = broker.getAgente(op.getRefAgenteID());
            if (ag != null) {
                if (op.getTipo().equals("compra")) {
                    ag.setOperacionCompra(op);
                } else {
                    ag.setOperacionVenta(op);
                }
            }
        }
    }
}
