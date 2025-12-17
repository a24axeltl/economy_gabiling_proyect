/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mycompany.psphilosbroker.DataSaveOperacionUtilies;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Agente;
import model.Operacion;
import view.CreateOperacionDialog;
import view.MainJFrame;

/**
 *
 * @author W10-Portable
 */
public class CreateOperacionController {
    private CreateOperacionDialog view;
    private MainJFrame listView;

    public CreateOperacionController(CreateOperacionDialog view, MainJFrame listView) {
        this.view = view;
        this.listView = listView;
        this.view.setCreateButtonActionListener(this.getCreateButtonActionListener());
        this.view.setCancelButtonActionListener(this.getCancelButtonActionListener());
    }
    
    private ActionListener getCreateButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agente refAgente = view.getSelectAgenteComboBox();
                String tipoOperacion = view.getSelectTypeOperacionComboBox();
                int cantidad = view.getCantidadSpinner();
                double precioLimite = view.getDoubleLimiteAgenteTextField();
                if(precioLimite <= 0){
                    JOptionPane.showMessageDialog(view, "Precio Invalido!", "Error de Precio", JOptionPane.ERROR_MESSAGE);
                } else {
                    Operacion newOperacion = new Operacion(tipoOperacion, precioLimite, cantidad, refAgente);
                    DataSaveOperacionUtilies.guardarOperacion(newOperacion);
                    
                    view.dispose();
                    listView.addOperacion(newOperacion.getIdRefAgente() + "|" + newOperacion.getTipo() + "|" + newOperacion.getLimite() + "|" + newOperacion.getCantidad());

                    JOptionPane.showMessageDialog(view, "Operacion Creada!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        return al;
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
}
