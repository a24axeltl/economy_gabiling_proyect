/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
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
    }
    
    private ActionListener getCreateAgentButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Scanner scanner = new Scanner(System.in);
                String agente = scanner.nextLine();
                view.addAgent(agente);
            }
        };
        return al;
    }
    
    private ActionListener getDeleteAgentButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.delItemList(view.getIndexItemList());
            }
        };
        return al;
    }
}
