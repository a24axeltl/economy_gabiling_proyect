/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author W10-Portable
 */
public class GraficaBolsa implements Runnable {

    private TimeSeries timeSeries; //Almacenaje del precio con su marca de tiempo para la representación gráfica.

    public GraficaBolsa() {
        this.timeSeries = new TimeSeries("Precio de la Operacion");
    }

    public ChartPanel getGraficoChart() { //Metodo para obtener el modelo grafico (ChartPanel). Se usara en el MainJFrame
        TimeSeriesCollection dataset = new TimeSeriesCollection(timeSeries); //Almacenaje del timeSeries para procesarlo en la grafica, albergando varios datos para su lectura.

        JFreeChart chart = ChartFactory.createTimeSeriesChart("Grafico Precio", "X", "Precio", dataset); //Creación del grafico, configurando (Titulo del Grafico, Nombre del Eje X, Nombre del Eje Y, Datos con el dataset).

        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

    public void addPrecio(double precio) { //Metodo para guardar el precio en timeSeries.
        timeSeries.addOrUpdate(new Millisecond(), precio); //Añadir la marca temporal y el precio a timeSeries (X,Y).
    }

    @Override
    public void run() {
        double precioActual = 1000; //Precio inicial
        while (true) {
            SwingUtilities.invokeLater(() -> addPrecio(precioActual)); //Actualizacion del hilo.
            try {
                Thread.sleep(300); //Frecuencia de actualizacion
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }
    }
}
