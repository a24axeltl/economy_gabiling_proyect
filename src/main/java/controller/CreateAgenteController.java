/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Agente;
import com.mycompany.psphilosbroker.DataSaveAgenteUtilies;
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
                int id = 0;
                for(Agente ag : DataSaveAgenteUtilies.cargarAgentes()){
                    if(ag.getID() >= id){
                        id = ag.getID() + 1;
                    }
                }
                String nombre = view.getTextNombreAgenteTextField();
                double saldo = view.getDoubleSaldoAgenteTextField();
                
                if(saldo <= 0){
                    JOptionPane.showMessageDialog(view, "Saldo Invalido!", "Error de Saldo", JOptionPane.ERROR_MESSAGE);
                } else {
                    Agente newAgente = new Agente(id, nombre, saldo);
                    DataSaveAgenteUtilies.guardarAgente(newAgente);

                    view.dispose();
                    listView.addAgent(newAgente.getID() + "|" + newAgente.getNome() + "|" + newAgente.getSaldo());

                    JOptionPane.showMessageDialog(view, "Agente Creado!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        return al;
    }
}
