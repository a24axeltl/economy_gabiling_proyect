/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.psphilosbroker;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Agente;
import model.Operacion;

/**
 *
 * @author W10-Portable
 */
public class DataSaveOperacionUtilies {
    private static Gson gson = new Gson();
    private static String FOLDER_NAME = "saveOps/";
    private static String FILE_FORMAT = ".json";
    
    public static void guardarOperacion(Operacion op, String tipoOperacion){
        checkExistingFolder();
        String saveName = checkTypeOperacion(tipoOperacion);
        
        try (FileWriter fw = new FileWriter(FOLDER_NAME + saveName + op.getRefAgenteID() + FILE_FORMAT)){
            gson.toJson(op, fw);
        } catch (IOException ex) {
            System.getLogger(PspHilosBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public static void eliminarOperacion(int id, String tipoOperacion){
        checkExistingFolder();
        String saveName = checkTypeOperacion(tipoOperacion);
        String nameFile = saveName + id + FILE_FORMAT;
        File[] saveFiles = new File(FOLDER_NAME).listFiles();
        
        for(File file : saveFiles){
            if(file.getName().equals(nameFile)){
                if(!file.delete()){
                    System.out.println("No se elimino el agente correctamente");
                }
            }
        }
    }
    
    public static Operacion cargarOperacion(int id){
        checkExistingFolder();
        File[] saveFiles = new File(FOLDER_NAME).listFiles();
        for(File saveFile : saveFiles){
            try (FileReader fr = new FileReader(saveFile)) {
                Operacion saveOp = gson.fromJson(fr, Operacion.class);
                if(saveOp.getRefAgenteID() == id){
                    return saveOp;
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return null;
    }
    
    public static ArrayList<Operacion> cargarOperaciones(){
        checkExistingFolder();
        File[] saveFiles = new File(FOLDER_NAME).listFiles();
        ArrayList<Operacion> listaOperacion = new ArrayList<>();
        for(File saveFile : saveFiles){
            try (FileReader fr = new FileReader(saveFile)) {
                Operacion saveOp = gson.fromJson(fr, Operacion.class);
                Agente ag = DataSaveAgenteUtilies.cargarAgente(saveOp.getRefAgenteID());
                saveOp.setRefAgente(ag);//Volver a cargar el refAgente tras la conversion JSON-Java
                listaOperacion.add(saveOp);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return listaOperacion;
    }
    
    private static void checkExistingFolder(){
        File saveFolder = new File(FOLDER_NAME);
        if(!saveFolder.exists()){
            saveFolder.mkdirs();
        }
    }
    
    private static String checkTypeOperacion(String tipoOperacion){
        if(tipoOperacion.equals("compra")){
            return "opCompra_";
        } else {
            return "opVenta_";
        }
    }
}
