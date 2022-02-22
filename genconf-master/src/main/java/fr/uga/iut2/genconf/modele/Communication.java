/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.iut2.genconf.modele;

import java.io.Serializable;

/**
 *
 * @author Asmaa
 */
public class Communication implements Serializable{
     private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    //Attributs
    private final String titreComm;
    private final String emailC;
    private final String emailO;
    
    //Constructeur
    public Communication(String titreComm, String emailC, String emailO){
        this.titreComm = titreComm;
        this.emailC = emailC;
        this.emailO = emailO;
    }
    
    //Méthodes
    
    public String getTitreComm(){
        return titreComm;
    }
    
    public String getEmailC(){
        return emailC;
    }
    
    public String getEmailO(){
        return emailO;
    }
        
}