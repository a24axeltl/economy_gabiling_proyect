/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mycompany.psphilosbroker.DataSaveAgenteUtilies;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.CreateAgenteDialog;
import view.CreateOperacionDialog;
import view.MainJFrame;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class FrontController {
    private MainJFrame view;

    public FrontController(MainJFrame view) {
        this.view = view;
        this.view.setCreateAgenteActionListener(this.getCreateAgenteButtonActionListener());
        this.view.setDeleteAgenteActionListener(this.getDeleteAgenteButtonActionListener());
        this.view.setCreateOperacionActionListener(this.getCreateOperacionButtonActionListener());
        this.view.setDeleteOperacionActionListener(this.getDeleteOperacionButtonActionListener());
    }
    
    private ActionListener getCreateAgenteButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CreateAgenteDialog caD = new CreateAgenteDialog(view, true);
                CreateAgenteController caC = new CreateAgenteController(caD,view);
                caD.setVisible(true);
            }
        };
        return al;
    }
    
    private ActionListener getDeleteAgenteButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectIndex = view.getIndexAgenteItemList();
                if(selectIndex == -1){
                    JOptionPane.showMessageDialog(view, "Seleccione un agente para proceder", "Aviso de Eliminar", JOptionPane.WARNING_MESSAGE);
                } else {
                    String itemList = view.getItemAgenteList(selectIndex);
                    String[] dataAgent = itemList.split("\\|");
                    int id = Integer.parseInt(dataAgent[0]);

                    DataSaveAgenteUtilies.eliminarAgente(id);
                    view.delItemAgenteList(view.getIndexAgenteItemList());

                    JOptionPane.showMessageDialog(view, "Agente Eliminado!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        return al;
    }
    
    private ActionListener getCreateOperacionButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CreateOperacionDialog coD = new CreateOperacionDialog(view, true);
                CreateOperacionController coC = new CreateOperacionController(coD,view);
                coD.setVisible(true);
            }
        };
        return al;
    }
    
    private ActionListener getDeleteOperacionButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        };
        return al;
    }
}
