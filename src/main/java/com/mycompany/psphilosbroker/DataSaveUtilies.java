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

/**
 *
 * @author W10-Portable
 */
public class DataSaveUtilies {
    private static Gson gson = new Gson();
    private static File[] saveFiles = new File("saveAgs/").listFiles();
    
    public static void guardarAgente(Agente ag){
        try (FileWriter fw = new FileWriter("saveAgs/ag_" + ag.getID() + ".json")){
            gson.toJson(ag, fw);
        } catch (IOException ex) {
            System.getLogger(PspHilosBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public static void eliminarAgente(int id){
        String nameFile = "ag_" + id + ".json";
        for(File file : saveFiles){
            if(file.getName().equals(nameFile)){
                file.delete();
            }
        }
    }
    
    public static Agente cargarAgente(int id){
        for(File saveFile : saveFiles){
            try (FileReader fr = new FileReader(saveFile)) {
                Agente saveAg = gson.fromJson(fr, Agente.class);
                if(saveAg.getID() != id){
                    return saveAg;
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return null;
    }
    
    public static ArrayList<Agente> cargarAgentes(){
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
