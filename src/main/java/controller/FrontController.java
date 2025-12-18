/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mycompany.psphilosbroker.DataSaveAgenteUtilies;
import com.mycompany.psphilosbroker.DataSaveOperacionUtilies;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartPanel;
import view.CreateAgenteDialog;
import view.CreateOperacionDialog;
import view.MainJFrame;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class FrontController {
    private MainJFrame view;
    private EstadoMercado broker;

    public FrontController(MainJFrame view, EstadoMercado broker) {
        this.view = view;
        this.broker = broker;
        this.view.setCreateAgenteActionListener(this.getCreateAgenteButtonActionListener());
        this.view.setDeleteAgenteActionListener(this.getDeleteAgenteButtonActionListener());
        this.view.setCreateOperacionActionListener(this.getCreateOperacionButtonActionListener());
        this.view.setDeleteOperacionActionListener(this.getDeleteOperacionButtonActionListener());
        loadGrafico();
    }
    
    private void loadGrafico(){
        GraficaBolsa graficoBolsa = new GraficaBolsa(broker);
        ChartPanel chartPanel = graficoBolsa.getGraficoChart(); //Obtener Grafico en ChartPanel.
        view.getBolsaPanel().setLayout(new BorderLayout());
        view.getBolsaPanel().add(chartPanel, BorderLayout.CENTER);
        view.setGrafiaBolsa(graficoBolsa);
        
        Thread hiloGrafica = new Thread(graficoBolsa);
        hiloGrafica.start();
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
                    String[] dataAgente = itemList.split("\\|");
                    int id = Integer.parseInt(dataAgente[0]);

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
                CreateOperacionController coC = new CreateOperacionController(coD,view,broker);
                coD.setVisible(true);
            }
        };
        return al;
    }
    
    private ActionListener getDeleteOperacionButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectIndex = view.getIndexOperacionItemList();
                if(selectIndex == -1){
                    JOptionPane.showMessageDialog(view, "Seleccione una operacion para proceder", "Aviso de Eliminar", JOptionPane.WARNING_MESSAGE);
                } else {
                    String itemList = view.getItemOperacionList(selectIndex);
                    String[] dataOperacion = itemList.split("\\|");
                    int id = Integer.parseInt(dataOperacion[0]);

                    DataSaveOperacionUtilies.eliminarOperacion(id, dataOperacion[1]);
                    view.delItemOperacionList(view.getIndexOperacionItemList());

                    JOptionPane.showMessageDialog(view, "Operacion Eliminada!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        return al;
    }
}
