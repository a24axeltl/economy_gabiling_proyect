/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mycompany.psphilosbroker.DataSaveUtilies;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.CreateAgenteDialog;
import view.MainJFrame;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class FrontController {
    private MainJFrame view;

    public FrontController(MainJFrame view) {
        this.view = view;
        this.view.setCreateAgentActionListener(this.getCreateAgentButtonActionListener());
        this.view.setDeleteAgentActionListener(this.getDeleteAgentButtonActionListener());
        this.view.loadSaveAgents();
    }
    
    private ActionListener getCreateAgentButtonActionListener(){
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
    
    private ActionListener getDeleteAgentButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectIndex = view.getIndexItemList();
                if(selectIndex == -1){
                    JOptionPane.showMessageDialog(view, "Seleccione un agente para proceder", "Aviso de Eliminar", JOptionPane.WARNING_MESSAGE);
                } else {
                    String itemList = view.getItemList(selectIndex);
                    String[] dataAgent = itemList.split("\\|");
                    int id = Integer.parseInt(dataAgent[0]);

                    DataSaveUtilies.eliminarAgente(id);
                    view.delItemList(view.getIndexItemList());

                    JOptionPane.showMessageDialog(view, "Agente Eliminado!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        return al;
    }
}
