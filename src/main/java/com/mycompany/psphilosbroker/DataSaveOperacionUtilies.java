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
import model.Operacion;

/**
 *
 * @author W10-Portable
 */
public class DataSaveOperacionUtilies {
    private static Gson gson = new Gson();
    private static String folderName = "saveOps/";
    
    public static void guardarOperacion(Operacion op){
        checkExistingFolder();
        try (FileWriter fw = new FileWriter(folderName + "op_" + op.getIdRefAgente() + ".json")){
            gson.toJson(op, fw);
        } catch (IOException ex) {
            System.getLogger(PspHilosBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public static void eliminarOperacion(int id){
        checkExistingFolder();
        String nameFile = "op_" + id + ".json";
        File[] saveFiles = new File(folderName).listFiles();
        
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
        File[] saveFiles = new File(folderName).listFiles();
        for(File saveFile : saveFiles){
            try (FileReader fr = new FileReader(saveFile)) {
                Operacion saveOp = gson.fromJson(fr, Operacion.class);
                if(saveOp.getIdRefAgente() == id){
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
        File[] saveFiles = new File(folderName).listFiles();
        ArrayList<Operacion> listaOperacion = new ArrayList<>();
        for(File saveFile : saveFiles){
            try (FileReader fr = new FileReader(saveFile)) {
                Operacion saveOp = gson.fromJson(fr, Operacion.class);
                listaOperacion.add(saveOp);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return listaOperacion;
    }
    
    private static void checkExistingFolder(){
        File saveFolder = new File(folderName);
        if(!saveFolder.exists()){
            saveFolder.mkdirs();
        }
    }
}
