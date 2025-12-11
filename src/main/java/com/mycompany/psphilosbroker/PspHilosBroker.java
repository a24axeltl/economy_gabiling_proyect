/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.psphilosbroker;

import controller.FrontController;
import view.MainJFrame;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class PspHilosBroker {

    public static void main(String[] args) {
        // PERSISTENCIA DE USUARIOS/AGENTES Y OPERACIONES
        
        // Guardar Agente...
        Agente ag1 = new Agente(1,"ag1",1200.00);
        ag1.nuevaOperacionCompra("compra", 10.5, 3.0);
        DataSaveUtilies.guardarAgente(ag1);
        
        //Cargar Agente...
        /*Agente loadAg = DataSaveUtilies.cargarAgente(1);
        if(loadAg != null){
            System.out.println(loadAg + loadAg.getOperacionCompra().toString());
        } else {
            System.out.println("Agente no encontrado");
        }*/
        
        // RECUPERAR EL PRECIO Y LOS VALORES ANTERIORES.
            
        // PINTAR PRECIO/TIEMPO (Posible uso de una GUI)
            
        //CREAR AGENTES CON OPERACIONES DE ENTRADA Y SALIDA
        //LECTURA PRECIO Y COMPRAN VENDEN -- 2 Tipos de Hilo
            
        // LOGICA DE COMPRAVENTA EN BROKER -- HILO (COMPRA VARIOS DE UN PRODUCTO, SUBIR PRECIO(OFERTA Y DEMANDA) | TENER MAXIMO DE COMPRA PRODUCTOS)
            
        //AGENTES CON UN CAPITAL QUE PUEDAN LANZAR OPERACIONES DE COMPRAVENTA
            
        // NUEVOS AGENTES
            
        // CREAR OPERACIONES
        
        /////////////////////////////////////////////////////////////////////////////
        
        MainJFrame frame = new MainJFrame();
        FrontController fc = new FrontController(frame);
        frame.setVisible(true);
    }
}
