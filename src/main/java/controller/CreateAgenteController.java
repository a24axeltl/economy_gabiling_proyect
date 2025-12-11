/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mycompany.psphilosbroker.Agente;
import com.mycompany.psphilosbroker.DataSaveUtilies;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.CreateAgenteDialog;
import view.MainJFrame;

/**
 *
 * @author W10-Portable
 */
public class CreateAgenteController {
    private CreateAgenteDialog view;
    private MainJFrame listView;

    public CreateAgenteController(CreateAgenteDialog view, MainJFrame listView) {
        this.view = view;
        this.listView = listView;
        this.view.setCancelButtonActionListener(this.getCancelButtonActionListener());
        this.view.setSaveButtonActionListener(this.getSaveButtonActionListener());
    }
    
    private ActionListener getCancelButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        };
        return al;
    }
    
    private ActionListener getSaveButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int) (Math.random() * 2147483647);
                for(Agente ag : DataSaveUtilies.cargarAgentes()){
                    while(ag.getID() == id){
                        id = (int) (Math.random() * 2147483647);
                    }
                }
                String nombre = view.getTextNombreAgenteTextField();
                double saldo = Double.parseDouble(view.getTextSaldoAgenteTextField());
                
                Agente newAgente = new Agente(id, nombre, saldo);
                DataSaveUtilies.guardarAgente(newAgente);
                
                view.dispose();
                listView.addAgent(id + "|" + nombre);
                
                JOptionPane.showMessageDialog(view, "Agente Creado!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        return al;
    }
}
