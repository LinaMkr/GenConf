/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.iut2.genconf.modele;

import java.io.Serializable;
import java.time.LocalTime;

/**
 *
 * @author Asmaa
 */
public class Session implements Serializable{
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    //attributs
    private final LocalTime heureDebut;
    private final LocalTime heureFin;
    private final String titre;
    private final String nomSalle;

    
    //Constructeur
    public Session(String titre, String nomSalle, LocalTime heureDebut, LocalTime heureFin){
        this.titre = titre;
        this.nomSalle = nomSalle;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }
    
    //Méthodes
    public LocalTime getHeureDebut(){
        return this.heureDebut;
    }
    
    public LocalTime getHeureFin(){
        return this.heureFin;
    }
    
    public String getTitre(){
        return this.titre;
    }
    
    public String getSalle(){
        return this.nomSalle;
    }
    
         
}