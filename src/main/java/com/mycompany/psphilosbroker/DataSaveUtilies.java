/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.psphilosbroker;

import model.Agente;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author W10-Portable
 */
public class DataSaveUtilies {
    private static Gson gson = new Gson();
    private static String folderName = "saveAgs/";

    public DataSaveUtilies() {
        File saveFolder = new File(folderName);
        if(!saveFolder.exists()){
            saveFolder.mkdirs();
        }
    }
    
    
    public static void guardarAgente(Agente ag){
        try (FileWriter fw = new FileWriter(folderName + "ag_" + ag.getID() + ".json")){
            gson.toJson(ag, fw);
        } catch (IOException ex) {
            System.getLogger(PspHilosBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public static void eliminarAgente(int id){
        String nameFile = "ag_" + id + ".json";
        File[] saveFiles = new File(folderName).listFiles();
        
        for(File file : saveFiles){
            if(file.getName().equals(nameFile)){
                if(!file.delete()){
                    System.out.println("No se elimino el agente correctamente");
                }
            }
        }
    }
    
    public static Agente cargarAgente(int id){
        File[] saveFiles = new File(folderName).listFiles();
        for(File saveFile : saveFiles){
            try (FileReader fr = new FileReader(saveFile)) {
                Agente saveAg = gson.fromJson(fr, Agente.class);
                if(saveAg.getID() == id){
                    return saveAg;
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return null;
    }
    
    public static ArrayList<Agente> cargarAgentes(){
        File[] saveFiles = new File(folderName).listFiles();
        ArrayList<Agente> listaAgente = new ArrayList<>();
        for(File saveFile : saveFiles){
            try (FileReader fr = new FileReader(saveFile)) {
                Agente saveAg = gson.fromJson(fr, Agente.class);
                listaAgente.add(saveAg);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return listaAgente;
    }
}
